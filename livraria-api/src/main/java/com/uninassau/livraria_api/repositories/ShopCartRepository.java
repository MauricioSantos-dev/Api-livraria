package com.uninassau.livraria_api.repositories;

import com.uninassau.livraria_api.entities.ShopCart;
import com.uninassau.livraria_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ShopCartRepository extends JpaRepository<ShopCart,Long> {

    Optional<ShopCart> findByUser(User user);

}
