package com.alabtaal.thecomputershop.mapper;

import com.alabtaal.thecomputershop.entity.ItemTransHeaderEntity;
import com.alabtaal.thecomputershop.model.ItemTransHeaderModel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {TransHeaderQualifier.class}
)
public interface TransHeaderMapper {
    



    ItemTransHeaderModel toModel(final ItemTransHeaderEntity entity);


    ItemTransHeaderEntity toEntity(final ItemTransHeaderModel model);



}
