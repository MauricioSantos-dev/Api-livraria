package com.uninassau.livraria_api.repositories;

import com.uninassau.livraria_api.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
