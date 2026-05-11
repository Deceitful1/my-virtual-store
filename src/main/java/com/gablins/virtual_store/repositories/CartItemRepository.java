package com.gablins.virtual_store.repositories;

import com.gablins.virtual_store.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long>
{
    List<CartItem> findByUserId(Long userId);
    Optional<CartItem> findByProductIdAndUserId(Long productId, Long userId);
}


