package com.uninassau.livraria_api.services;

import com.uninassau.livraria_api.dto.CartItemDTO;
import com.uninassau.livraria_api.dto.CartItemRequestDTO;
import com.uninassau.livraria_api.dto.ShoppingCartDTO;
import com.uninassau.livraria_api.entities.*;
import com.uninassau.livraria_api.repositories.BookRepository;
import com.uninassau.livraria_api.repositories.CartItemRepository;
import com.uninassau.livraria_api.repositories.ShoppingCartRepository;
import com.uninassau.livraria_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public ShoppingCart addcartItem(Long userId, Long bookId, CartItemRequestDTO cartItemRequestDTO) {
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



        Optional<CartItem> existingItemOpt = shoppingCart.getItems().stream()
                .filter(item -> item.getBook().getId().equals(bookId))
                .findFirst();

        if (existingItemOpt.isPresent()) {

            CartItem existingItem = existingItemOpt.get();

            int newQuantity = existingItem.getQuantity() + cartItemRequestDTO.getQuantity();
            existingItem.setQuantity(newQuantity);


            existingItem.setPrice(newQuantity * book.getPrice());

        } else {

            double finalprice = cartItemRequestDTO.getQuantity() * book.getPrice();
            CartItem cartItem = new CartItem();
            cartItem.setBook(book);
            cartItem.setCart(shoppingCart);
            cartItem.setQuantity(cartItemRequestDTO.getQuantity());
            cartItem.setPrice(finalprice);
            cartItem.setImgUrl(book.getImgUrl());
            cartItem.setTitle(book.getTitle());



            shoppingCart.getItems().add(cartItem);
        }




        shoppingCartRepository.save(shoppingCart);



        return shoppingCart;
    }

    public ShoppingCartDTO getcartItem(Long userId) {
        User user= userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("Usuario nao encontrado!")
        );
        ShoppingCart shoppingCart = shoppingCartRepository.findByUser(user).orElseThrow(
                () -> new RuntimeException("Nenhum item encontrado!")
        );


        return new ShoppingCartDTO(shoppingCart);

    }
    public CartItem removeItemCart(Long cartItemID) {

        CartItem cartItem = cartItemRepository.findById(cartItemID).orElseThrow(
                () -> new RuntimeException("Item nao encontrado!")
        );
        ShoppingCart shoppingCart =  cartItem.getCart();


        Book book = cartItem.getBook();


        int quantity = cartItem.getQuantity() - 1;

            cartItem.setQuantity(quantity);
            double newprice = book.getPrice() * cartItem.getQuantity();
            cartItem.setPrice(newprice);

            if (quantity <= 0) {

                shoppingCart.getItems().remove(cartItem);
            }

        return cartItemRepository.save(cartItem);

    }



}
