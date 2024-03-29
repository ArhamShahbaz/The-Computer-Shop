package com.alabtaal.thecomputershop.mapper;


import com.alabtaal.thecomputershop.entity.RoleEntity;
import com.alabtaal.thecomputershop.entity.UserEntity;
import com.alabtaal.thecomputershop.model.UserSummary;
import com.alabtaal.thecomputershop.payload.request.SignupRequest;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
    uses = UserQualifier.class,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

  @Mapping(target = "password", qualifiedByName = "password")
  @Mapping(target = "roles", qualifiedByName = "rolesEntity")
  UserEntity toEntity(final SignupRequest request);

  @Mapping(target = "roles", qualifiedByName = "roles")
  UserSummary toUserSummary(final UserEntity entity);




}
