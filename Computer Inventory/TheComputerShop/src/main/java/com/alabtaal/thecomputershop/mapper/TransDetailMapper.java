package com.alabtaal.thecomputershop.mapper;


import com.alabtaal.thecomputershop.entity.ItemTransDetailEntity;
import com.alabtaal.thecomputershop.model.ItemTransDetailModel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface TransDetailMapper {



    ItemTransDetailModel toModel(final ItemTransDetailEntity entity);


    ItemTransDetailEntity toEntity(final ItemTransDetailModel model);
}
