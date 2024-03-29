package com.alabtaal.thecomputershop.repo;


import com.alabtaal.thecomputershop.entity.ItemAdjustmentEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public interface ItemAdjustmentRepository extends JpaRepository<ItemAdjustmentEntity, UUID> {




}