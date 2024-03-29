//package com.alabtaal.thecomputershop.validation;
//
//
//
//import com.alabtaal.thecomputershop.entity.ItemEntity;
//import jakarta.validation.ConstraintValidatorContext;
//import lombok.RequiredArgsConstructor;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//public class ChildExistValidator implements ConstraintValidator<ChildExistValidation, ItemEntity> {
//
//    final ItemAdjustmentRepository itemAdjustmentRepository;
//
//    final ItemTransDetailRepository itemTransDetailRepository;
//
//    @Override
//    public boolean isValid(ItemEntity entity, ConstraintValidatorContext constraintValidatorContext) {
//        return !hasChildren(entity);
//    }
//
//    private Boolean hasChildren(final ItemEntity entity) {
//        if (entity != null) {
//            List<ItemAdjustmentEntity> itemAdjustmentEntities = itemAdjustmentRepository.findByItem(entity);
//            List<ItemTransDetailEntity> itemTransDetailEntities = itemTransDetailRepository.findByItem(entity);
//            return !itemAdjustmentEntities.isEmpty() || !itemTransDetailEntities.isEmpty();
//        }
//        return false;
//    }
//}
