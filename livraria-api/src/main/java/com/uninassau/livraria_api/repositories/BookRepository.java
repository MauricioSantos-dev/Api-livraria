package com.uninassau.livraria_api.repositories;

import com.uninassau.livraria_api.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookRepository extends JpaRepository<Book,Long> {
    public List<Book> findByCategory(String category);

    public List<Book> findByTitleContainingIgnoreCase(String title);

}
