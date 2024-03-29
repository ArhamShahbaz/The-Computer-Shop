package com.alabtaal.thecomputershop.mapper;


import com.alabtaal.thecomputershop.entity.ItemEntity;
import com.alabtaal.thecomputershop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ItemQualifier {


    private final ItemService itemService;


    @Named("itemEntity")
    public ItemEntity toEntity(final UUID uuid) {
        return itemService.findById(uuid);
    }

}
