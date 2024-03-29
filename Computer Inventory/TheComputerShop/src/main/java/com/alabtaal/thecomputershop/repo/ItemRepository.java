package com.alabtaal.thecomputershop.repo;

import com.alabtaal.thecomputershop.entity.ItemEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, UUID>,
        JpaSpecificationExecutor<ItemEntity> {

    @Query(
            nativeQuery = true,
            value =
                    "select coalesce(sum(quantity), 0) from inventory.item_trans_vu where"
                            + " item_id = :item")
    Double getItemQuantity(
            @Param("item") final UUID item);

    Optional<ItemEntity> findByName(final String name);
}