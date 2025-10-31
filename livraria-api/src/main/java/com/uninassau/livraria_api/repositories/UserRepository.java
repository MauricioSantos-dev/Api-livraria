package com.uninassau.livraria_api.repositories;

import com.uninassau.livraria_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
