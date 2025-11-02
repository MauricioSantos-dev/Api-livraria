package com.uninassau.livraria_api.controllers;

import com.uninassau.livraria_api.entities.Book;
import com.uninassau.livraria_api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity findAll() {

        try {
            List<Book> books = bookService.findAll();
            if(books.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok().body(books);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity findByCategory(@PathVariable String category) {
        try {
            List<Book> books = bookService.findByCategory(category);
            if (books.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return  ResponseEntity.ok().body(books);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
