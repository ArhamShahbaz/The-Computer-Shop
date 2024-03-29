package com.alabtaal.thecomputershop.service;




import com.alabtaal.thecomputershop.entity.ItemAdjustmentEntity;
import com.alabtaal.thecomputershop.execptions.ResourceNotFoundException;
import com.alabtaal.thecomputershop.mapper.ItemAdjustmentMapper;
import com.alabtaal.thecomputershop.model.ItemAdjustmentModel;
import com.alabtaal.thecomputershop.repo.ItemAdjustmentRepository;
import com.alabtaal.thecomputershop.utils.Miscellaneous;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ItemAdjustmentServiceImpl implements ItemAdjustmentService {


    private final ItemAdjustmentRepository itemAdjustmentRepository;

    private final ItemAdjustmentMapper itemAdjustmentMapper;

    @Override
    public ItemAdjustmentModel save(ItemAdjustmentModel entity) throws BadRequestException {
        Miscellaneous.constraintViolation(entity);

        ItemAdjustmentEntity mapperEntity = itemAdjustmentMapper.toEntity(entity);

        ItemAdjustmentEntity saved = itemAdjustmentRepository.save(mapperEntity);

        return itemAdjustmentMapper.toModel(saved);
    }

    @Override
    public ItemAdjustmentModel update(ItemAdjustmentModel model, UUID uuid) throws BadRequestException {
        ItemAdjustmentEntity itemAdjustmentEntity = itemAdjustmentRepository.findById(uuid).orElseThrow(
                () -> new ResourceNotFoundException("ItemAdjustment", "Id", uuid)
        );
        Miscellaneous.constraintViolation(model);
        ItemAdjustmentEntity mapperEntity = itemAdjustmentMapper.toEntity(model);
        itemAdjustmentEntity.setItem(mapperEntity.getItem());
        itemAdjustmentEntity.setType(mapperEntity.getType());
        itemAdjustmentEntity.setAdjustmentDate(mapperEntity.getAdjustmentDate());
        itemAdjustmentEntity.setQuantity(mapperEntity.getQuantity());
        itemAdjustmentEntity.setAdjustmentReason(mapperEntity.getAdjustmentReason());
        return itemAdjustmentMapper.toModel(itemAdjustmentEntity);
    }

    @Override
    public List<ItemAdjustmentModel> findAll() {
        return itemAdjustmentMapper.toModels(itemAdjustmentRepository.findAll());
    }

    @Override
    public ItemAdjustmentModel findById(UUID uuid) throws ResourceNotFoundException {
        ItemAdjustmentEntity itemAdjustmentEntity = itemAdjustmentRepository.findById(uuid).orElseThrow(
                () -> new ResourceNotFoundException("ItemAdjustment", "Id", uuid)
        );
        return itemAdjustmentMapper.toModel(itemAdjustmentEntity);
    }



    @Override
    public void deleteById(UUID uuid) {
        itemAdjustmentRepository.deleteById(uuid);
    }




}
