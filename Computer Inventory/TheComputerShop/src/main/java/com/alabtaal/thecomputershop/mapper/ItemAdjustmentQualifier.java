package com.alabtaal.thecomputershop.mapper;



import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
@RequiredArgsConstructor
public class ItemAdjustmentQualifier {





    @Named("dataFormatTE")
    public LocalDateTime dataFormatTE(final String dateString) {
      return  LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


    }
//public LocalDateTime dataFormatTE(final String dateString) {
//        LocalDateTime dateTime = null;
//        try {
//            // Try parsing with format including seconds (yyyy-MM-dd HH:mm:ss)
//             dateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            // Handle the parsed date-time
//        } catch (DateTimeParseException e) {
//            // If parsing fails with seconds, try parsing with format excluding seconds (yyyy-MM-dd)
//            try {
//                 dateTime = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
//                // Handle the parsed date (assuming you only need the date part)
//            } catch (DateTimeParseException e2) {
//                // Handle parsing failure for both formats
//            }
//        }
//        return dateTime;
//    }
    @Named("dataFormatTM")
    public String dataFormatTM(final LocalDateTime date) {
        return String.valueOf(date);
    }

}
