package com.uninassau.livraria_api.repositories;

import com.uninassau.livraria_api.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BookRepository extends JpaRepository<Book,Long> {
}
