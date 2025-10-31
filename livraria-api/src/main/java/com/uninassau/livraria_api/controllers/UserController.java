package com.uninassau.livraria_api.controllers;

import com.uninassau.livraria_api.entities.Book;
import com.uninassau.livraria_api.entities.User;
import com.uninassau.livraria_api.repositories.UserRepository;
import com.uninassau.livraria_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/{userId}/addwishlist/{bookId}")
    public ResponseEntity addWishlist(@PathVariable Long userId, @PathVariable Long bookId) {
        try {
            User userupdate = userService.addWishlist(userId, bookId);
            return ResponseEntity.ok().build();

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest().build();
        }

    }
        @GetMapping("/{userId}/wishlist")
        public ResponseEntity getWishlist(@PathVariable Long userId){

        try {
            List <Book> wishlist= userService.getWishlist(userId);
            return ResponseEntity.ok().body(wishlist);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
        }

}
