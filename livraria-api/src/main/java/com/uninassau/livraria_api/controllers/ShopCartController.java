package com.uninassau.livraria_api.controllers;

import com.uninassau.livraria_api.entities.CartItemDTO;
import com.uninassau.livraria_api.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShopCartController {

    @Autowired
    private ShopCartService shopCartService;

    @PostMapping("/{userId}/{bookId}/shopcart")
    public ResponseEntity addCartItem(@PathVariable Long userId, @PathVariable Long bookId, @RequestBody CartItemDTO cartItemDTO){
        try {
            shopCartService.addcartItem(userId, bookId, cartItemDTO);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
