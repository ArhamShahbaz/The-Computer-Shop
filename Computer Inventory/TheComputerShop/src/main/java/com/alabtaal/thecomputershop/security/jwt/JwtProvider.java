package com.alabtaal.thecomputershop.security.jwt;



import com.alabtaal.thecomputershop.model.UserPrincipal;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtProvider {

    private static final Logger LOG = LoggerFactory.getLogger(JwtProvider.class);
    private static final String INV_SECRET_KEY = System.getenv("INV_SECRET_KEY-JWT");


    private static final byte[] keyBytes = INV_SECRET_KEY.getBytes(StandardCharsets.UTF_8);
    private static final SecretKey secret = Keys.hmacShaKeyFor(keyBytes);

    @Value("${abt.app.jwtExpiration:#{1000*24*60*60}}")
    private int jwtExpiration;

    public String generateJwt(final Authentication authentication) {
        final UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return Jwts
                .builder()
                .subject(userPrincipal.getUsername())
                .issuedAt(new Date())
//                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(secret, Jwts.SIG.HS256)
                .compact();
    }

    public String getUsernameFromJwt(final String jwt) {

        return Jwts
                .parser()
                .verifyWith(secret)
                .build()
                .parseSignedClaims(jwt)
                .getPayload()
                .getSubject();
    }

    public boolean validateJwtToken(final String jwt) {
        try {
            Jwts
                    .parser()
                    .verifyWith(secret)
                    .build()
                    .parse(jwt);
            return true;
        } catch (SignatureException e) {
            throw new RuntimeException("Invalid JWT Signature");
        } catch (MalformedJwtException e) {
            throw new RuntimeException("Invalid JWT");
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Expired JWT");
        } catch (UnsupportedJwtException e) {
            throw new RuntimeException("Unsupported JWT");
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("JWT claims are invalid");
        }
    }
}
