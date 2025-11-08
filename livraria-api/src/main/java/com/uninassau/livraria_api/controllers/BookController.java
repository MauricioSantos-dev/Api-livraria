package com.uninassau.livraria_api.controllers;

import com.uninassau.livraria_api.entities.Book;
import com.uninassau.livraria_api.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}/book")
    public ResponseEntity findById(@PathVariable Long id) {
        try {
            Book book = bookService.findById(id);
            return  ResponseEntity.ok().body(book);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity findByTitle(@RequestParam String title) {
        try {
           List<Book> book= bookService.findByTitle(title);
            return  ResponseEntity.ok().body(book);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
