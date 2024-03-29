package com.alabtaal.thecomputershop.service;


import com.alabtaal.thecomputershop.entity.ItemEntity;
import com.alabtaal.thecomputershop.execptions.ResourceNotFoundException;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface ItemService {


    ItemEntity save(@Valid final ItemEntity entity);

    ItemEntity update(@Valid final ItemEntity entity , final UUID itemId);


    List<ItemEntity> findAll();
    ItemEntity findById(final UUID itemId) throws ResourceNotFoundException;

    void delete(final ItemEntity model);

    void deleteById(final UUID itemId);









}
