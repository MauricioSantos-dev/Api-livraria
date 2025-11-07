package com.uninassau.livraria_api.controllers;

import com.uninassau.livraria_api.entities.CartItem;
import com.uninassau.livraria_api.entities.CartItemDTO;
import com.uninassau.livraria_api.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShopCartController {

    @Autowired
    private ShopCartService shopCartService;

    @PostMapping("/{userId}/{bookId}/addcartitem")
    public ResponseEntity addCartItem(@PathVariable Long userId, @PathVariable Long bookId, @RequestBody CartItemDTO cartItemDTO){
        try {
            shopCartService.addcartItem(userId, bookId, cartItemDTO);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("{userId}/shopcart")
    public ResponseEntity getShopCart(@PathVariable Long userId){
        try {
           List<CartItem> getcartItem= shopCartService.getcartItem(userId);
            return ResponseEntity.ok(getcartItem);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
