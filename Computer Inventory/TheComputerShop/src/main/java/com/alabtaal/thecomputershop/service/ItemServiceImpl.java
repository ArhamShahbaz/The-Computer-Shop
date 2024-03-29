package com.alabtaal.thecomputershop.service;


import com.alabtaal.thecomputershop.entity.ItemEntity;
import com.alabtaal.thecomputershop.execptions.ResourceNotFoundException;

import com.alabtaal.thecomputershop.repo.ItemRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepo;

    


    @Override
    public ItemEntity save(@Valid ItemEntity entity) {


        return itemRepo.save(entity);
    }

    @Override
    public ItemEntity update(ItemEntity entity, UUID itemId) {

        ItemEntity itemEntity = findById(itemId);

        itemEntity.setGeneration(entity.getGeneration());
        itemEntity.setName(entity.getName());
        itemEntity.setRam(entity.getRam());
        itemEntity.setRom(entity.getRom());
        itemEntity.setModel(entity.getModel());
        itemEntity.setRemarks(entity.getRemarks());
        itemEntity.setPurchaseRate(entity.getPurchaseRate());
        itemEntity.setStockItem(entity.getStockItem());
        itemEntity.setSaleRate(entity.getSaleRate());

        return itemEntity;
    }


    @Override
    public List<ItemEntity> findAll() {

        return itemRepo.findAll();
    }

    @Override
    public ItemEntity findById(UUID userId) throws ResourceNotFoundException {

        return itemRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
    }

    @Override
    public void delete(ItemEntity entity) {
        itemRepo.delete(entity);

    }

    @Override
    public void deleteById(UUID userId) {
        delete(findById(userId));
    }
}
