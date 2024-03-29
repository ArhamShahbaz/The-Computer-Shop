package com.alabtaal.thecomputershop.repo;


import com.alabtaal.thecomputershop.entity.ItemTransHeaderEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemTransHeaderRepository extends JpaRepository<ItemTransHeaderEntity, UUID>,
        JpaSpecificationExecutor<ItemTransHeaderEntity> {


}
