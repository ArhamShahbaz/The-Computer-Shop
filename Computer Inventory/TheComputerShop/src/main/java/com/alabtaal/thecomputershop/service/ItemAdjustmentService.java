package com.alabtaal.thecomputershop.service;


import com.alabtaal.thecomputershop.execptions.ResourceNotFoundException;
import com.alabtaal.thecomputershop.model.ItemAdjustmentModel;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;

import java.util.List;
import java.util.UUID;

public interface ItemAdjustmentService {


    ItemAdjustmentModel save(@Valid final ItemAdjustmentModel entity) throws BadRequestException;

    ItemAdjustmentModel update(@Valid final ItemAdjustmentModel entity , final UUID itemId) throws BadRequestException;


    List<ItemAdjustmentModel> findAll();
    ItemAdjustmentModel findById(final UUID itemId) throws ResourceNotFoundException;


    void deleteById(final UUID itemId);









}
