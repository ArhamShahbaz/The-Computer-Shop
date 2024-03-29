package com.alabtaal.thecomputershop.mapper;

import com.alabtaal.thecomputershop.entity.ItemEntity;
import com.alabtaal.thecomputershop.model.ItemModel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ItemMapper {
    


    ItemModel toModel(final ItemEntity ItemEntity);


    ItemEntity toEntity(final ItemModel model);

    List<ItemModel> toModels(final List<ItemEntity> ItemEntities);


    List<ItemEntity> toEntities(final List<ItemModel> model);



}
