package com.alabtaal.thecomputershop.validation;


import com.alabtaal.thecomputershop.entity.ItemAdjustmentEntity;
import com.alabtaal.thecomputershop.enums.ItemAdjustmentType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public class AdjustmentReasonValidator implements
        ConstraintValidator<ValidateAdjustmentReason, ItemAdjustmentEntity> {

    @Override
    public boolean isValid(final ItemAdjustmentEntity adjustment,
                           final ConstraintValidatorContext context) {
        final ItemAdjustmentEntity itemAdjustment = Optional.ofNullable(adjustment)
                .orElseGet(ItemAdjustmentEntity::new);
        return ItemAdjustmentType.OPENING_STOCK.equals(itemAdjustment.getType())
                || StringUtils.isNotBlank(itemAdjustment.getAdjustmentReason());
    }
}
