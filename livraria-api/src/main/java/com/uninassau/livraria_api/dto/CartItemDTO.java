package com.uninassau.livraria_api.dto;

import com.uninassau.livraria_api.entities.CartItem;

public class CartItemDTO {

    private Long id;
    private Long bookId;
    private String title;
    private int quantity;
    private double price;
    private String imgUrl;




    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CartItemDTO() {
    }

    public CartItemDTO(CartItem cartItem) {
        this.id = cartItem.getId();
        this.bookId = cartItem.getBook().getId();
        this.title = cartItem.getTitle();
        this.quantity = cartItem.getQuantity();
        this.price = cartItem.getPrice();
        this.imgUrl = cartItem.getImgUrl();

    }

}
