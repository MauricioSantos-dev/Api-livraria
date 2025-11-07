package com.uninassau.livraria_api.services;

import com.uninassau.livraria_api.entities.*;
import com.uninassau.livraria_api.repositories.BookRepository;
import com.uninassau.livraria_api.repositories.ShoppingCartRepository;
import com.uninassau.livraria_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;



    public ShoppingCart addcartItem(Long userId, Long bookId, CartItemDTO cartItemDTO) {
        User user= userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("Usuario nao encontrado!")
        );
        Book book= bookRepository.findById(bookId).orElseThrow(
                () -> new RuntimeException("Livro nao encontrado!")
        );
        ShoppingCart shoppingCart = shoppingCartRepository.findByUser(user)
                .orElseGet(() -> {
                    ShoppingCart novoCart = new ShoppingCart();
                    novoCart.setUser(user);
                    return shoppingCartRepository.save(novoCart);
                });

        CartItem cartItem = new CartItem();
        cartItem.setBook(book);
        cartItem.setCart(shoppingCart);
        cartItem.setQuantity(cartItemDTO.getQuantity());

        shoppingCart.getItems().add(cartItem);

        shoppingCartRepository.save(shoppingCart);

        return shoppingCart;
    }

    public List<CartItem> getcartItem(Long userId) {
        User user= userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("Usuario nao encontrado!")
        );
        ShoppingCart shoppingCart = shoppingCartRepository.findByUser(user).orElseThrow(
                () -> new RuntimeException("Nenhum item encontrado!")
        );

       return shoppingCart.getItems();

    }

}
