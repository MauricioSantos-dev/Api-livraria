package com.uninassau.livraria_api.repositories;

import com.uninassau.livraria_api.entities.ShoppingCart;
import com.uninassau.livraria_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {

    Optional<ShoppingCart> findByUser(User user);

}
