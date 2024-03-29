package com.alabtaal.thecomputershop.mapper;


import com.alabtaal.thecomputershop.entity.ItemAdjustmentEntity;
import com.alabtaal.thecomputershop.model.ItemAdjustmentModel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {ItemQualifier.class,ItemAdjustmentQualifier.class})
public interface ItemAdjustmentMapper {



    @Mapping(target = "adjustmentDate" , qualifiedByName = "dataFormatTE")
    @Mapping(target = "item",source = "itemId", qualifiedByName = "itemEntity")
    ItemAdjustmentEntity toEntity(final ItemAdjustmentModel model);



    @Mapping(target = "adjustmentDate" , qualifiedByName = "dataFormatTM")
    @Mapping(source = "entity.item.name", target = "name")
    @Mapping(source = "entity.item.rom", target = "rom")
    @Mapping(source = "entity.item.ram", target = "ram")
    @Mapping(source = "entity.item.generation", target = "generation")
    @Mapping(source = "entity.item.model", target = "model")
    @Mapping(target = "itemId", source = "entity.item.itemId")
    ItemAdjustmentModel toModel(final ItemAdjustmentEntity entity);


    //    @Mapping(target = "item" ,qualifiedByName = "itemEntity")

    default List<ItemAdjustmentModel> toModels(List<ItemAdjustmentEntity> entities){
        return entities
                .stream()
                .map(this::toModel)
                .toList();
    }


    default List<ItemAdjustmentEntity> toEntities(List<ItemAdjustmentModel> models){
        return models
                .stream()
                .map(this::toEntity)
                .toList();
    }

}
