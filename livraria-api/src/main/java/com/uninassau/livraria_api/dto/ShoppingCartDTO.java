package com.uninassau.livraria_api.dto;

import com.uninassau.livraria_api.entities.CartItem;
import com.uninassau.livraria_api.entities.ShoppingCart;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartDTO {

    private Long id;

    private Double price;

    private List<CartItemDTO> cartItemDTOList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<CartItemDTO> getCartItemDTOList() {
        return cartItemDTOList;
    }

    public void setCartItemDTOList(List<CartItemDTO> cartItemDTOList) {
        this.cartItemDTOList = cartItemDTOList;
    }

    public ShoppingCartDTO() {
    }

    public ShoppingCartDTO(ShoppingCart cart) {
        this.id = cart.getId();

        this.cartItemDTOList = cart.getItems()
                .stream()
                .map(CartItemDTO::new)
                .collect(Collectors.toList());

        this.price = cart.getItems()
                .stream()
                .mapToDouble(CartItem::getPrice)
                .sum();
    }
}
