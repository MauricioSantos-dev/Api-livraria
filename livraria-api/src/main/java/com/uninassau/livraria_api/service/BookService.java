package com.uninassau.livraria_api.service;

import com.uninassau.livraria_api.entities.Book;
import com.uninassau.livraria_api.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookService {

@Autowired
private BookRepository bookRepository;

    public List<Book> findAll() {
        List<Book> books = bookRepository.findAll();


        return books.stream().filter(book -> book.getStock() > 0).toList();
    }

    public List<Book> findByCategory(String category) {
       return bookRepository.findByCategory(category);

    }


}
