package com.uninassau.livraria_api.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    @NotBlank
    @Email
    private String email;

    @ManyToMany
    @JoinTable(
            name = "wishlist",
            joinColumns = @JoinColumn (
                    name = "user_id"),
            inverseJoinColumns = @JoinColumn( name= "book_id")
    )
    private List<Book> wishlist;

}
