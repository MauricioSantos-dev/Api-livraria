package com.uninassau.livraria_api.controllers;

import com.uninassau.livraria_api.dto.CartItemDTO;
import com.uninassau.livraria_api.dto.CartItemRequestDTO;
import com.uninassau.livraria_api.dto.ShoppingCartDTO;
import com.uninassau.livraria_api.entities.User;
import com.uninassau.livraria_api.repositories.UserRepository;
import com.uninassau.livraria_api.services.ShoppingCartService;
import com.uninassau.livraria_api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/{bookId}/addcartitem")
    public ResponseEntity addCartItem(Principal principal, @PathVariable Long bookId, @RequestBody CartItemRequestDTO cartItemRequestDTO){
        try {
            String email= principal.getName();
            User user = userRepository.findByEmail(email);
                    if(user==null) {
                    throw new RuntimeException("Usuário não encontrado");
                    }

            shoppingCartService.addcartItem(user.getId(), bookId, cartItemRequestDTO);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/shopcart")
    public ResponseEntity getShopCart(Principal principal){
        try {
            String email= principal.getName();
            User user = userRepository.findByEmail(email);
            if(user==null) {
                throw new RuntimeException("Usuário não encontrado");
            }
           ShoppingCartDTO getcartItem= shoppingCartService.getcartItem(user.getId());
            return ResponseEntity.ok(getcartItem);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{cartItemID}/removeitem")
    public ResponseEntity removeItemCart(@PathVariable Long cartItemID, Principal principal)
    {
        try {
            String email= principal.getName();
            User user = userRepository.findByEmail(email);
            shoppingCartService.removeItemCart(cartItemID, user.getId());
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{cartItemID}/additem")
    public ResponseEntity addItemCart(@PathVariable Long cartItemID,  Principal principal) {
        try {
            String email= principal.getName();
            User user = userRepository.findByEmail(email);
            shoppingCartService.addItemCart(cartItemID, user.getId());
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{ShoppingCartId}/removeall")
    public ResponseEntity removeAll(Principal principal, @PathVariable Long ShoppingCartId) {
        try {
            String email= principal.getName();
            User user = userRepository.findByEmail(email);
            shoppingCartService.removeAll(ShoppingCartId, user.getId());
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }
    }

