package com.uninassau.livraria_api.services;

import com.uninassau.livraria_api.entities.User;
import com.uninassau.livraria_api.entities.Book;
import com.uninassau.livraria_api.repositories.BookRepository;
import com.uninassau.livraria_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;



    public User addWishlist(Long userId, Long bookId){

        User user= userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("Usuario nao encontrado!")
        );
        Book book= bookRepository.findById(bookId).orElseThrow(
                () -> new RuntimeException("Livro nao encontrado!")
        );

        user.getWishlist().add(book);
        return userRepository.save(user);
    }

    public List<Book> getWishlist(Long userId){
        User user= userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("Usuario nao encontrado!")
        );


        return user.getWishlist();
    }



}
