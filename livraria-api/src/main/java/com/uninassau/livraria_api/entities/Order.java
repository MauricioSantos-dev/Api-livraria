package com.uninassau.livraria_api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany
    @JoinColumn(name = "CartItem_id")
    private List<CartItem> cartItem = new ArrayList<>();

    private double price;

    public Order(Long id, LocalDateTime createdAt, List<CartItem> cartItem) {
        this.id = id;
        this.createdAt = createdAt;
        this.cartItem = cartItem;
        this.price = getCartItem().stream()
                .mapToDouble(CartItem::getPrice)
                .sum();
    }
}
