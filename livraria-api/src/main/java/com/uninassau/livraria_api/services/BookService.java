package com.uninassau.livraria_api.services;

import com.uninassau.livraria_api.entities.Book;
import com.uninassau.livraria_api.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Livro nao encontrado!")
        );
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }


}
