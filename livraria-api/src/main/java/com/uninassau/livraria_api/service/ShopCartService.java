package com.uninassau.livraria_api.service;

import com.uninassau.livraria_api.entities.*;
import com.uninassau.livraria_api.repositories.BookRepository;
import com.uninassau.livraria_api.repositories.CartItemRepository;
import com.uninassau.livraria_api.repositories.ShopCartRepository;
import com.uninassau.livraria_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopCartService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ShopCartRepository shopCartRepository;



    public ShopCart addcartItem(Long userId, Long bookId, CartItemDTO cartItemDTO) {
        User user= userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("Usuario nao encontrado!")
        );
        Book book= bookRepository.findById(bookId).orElseThrow(
                () -> new RuntimeException("Livro nao encontrado!")
        );
        ShopCart shopCart = shopCartRepository.findByUser(user)
                .orElseGet(() -> {
                    ShopCart novoCart = new ShopCart();
                    novoCart.setUser(user);
                    return shopCartRepository.save(novoCart);
                });

        CartItem cartItem = new CartItem();
        cartItem.setBook(book);
        cartItem.setCart(shopCart);
        cartItem.setQuantity(cartItemDTO.getQuantity());

        shopCart.getItems().add(cartItem);

        shopCartRepository.save(shopCart);

        return shopCart;
    }

}
