package com.uninassau.livraria_api.controllers;

import com.uninassau.livraria_api.dto.CartItemDTO;
import com.uninassau.livraria_api.dto.CartItemRequestDTO;
import com.uninassau.livraria_api.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/{userId}/{bookId}/addcartitem")
    public ResponseEntity addCartItem(@PathVariable Long userId, @PathVariable Long bookId, @RequestBody CartItemRequestDTO cartItemRequestDTO){
        try {
            shoppingCartService.addcartItem(userId, bookId, cartItemRequestDTO);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("{userId}/shopcart")
    public ResponseEntity getShopCart(@PathVariable Long userId){
        try {
           List<CartItemDTO> getcartItem= shoppingCartService.getcartItem(userId);
            return ResponseEntity.ok(getcartItem);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
