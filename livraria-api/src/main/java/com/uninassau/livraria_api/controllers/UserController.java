package com.uninassau.livraria_api.controllers;

import com.uninassau.livraria_api.entities.Book;
import com.uninassau.livraria_api.entities.User;
import com.uninassau.livraria_api.repositories.UserRepository;
import com.uninassau.livraria_api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/addwishlist/{bookId}")
    public ResponseEntity addWishlist(Principal principal, @PathVariable Long bookId) {
        try {
            String email= principal.getName();
            User user= userRepository.findByEmail(email);
            User userupdate = userService.addWishlist(user.getId(), bookId);
            return ResponseEntity.ok().build();

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest().build();
        }

    }
        @GetMapping("/wishlist")
        public ResponseEntity getWishlist(Principal principal) {

        try {
            String email= principal.getName();
            User user= userRepository.findByEmail(email);
            List <Book> wishlist= userService.getWishlist(user.getId());
            return ResponseEntity.ok().body(wishlist);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
        }

}
