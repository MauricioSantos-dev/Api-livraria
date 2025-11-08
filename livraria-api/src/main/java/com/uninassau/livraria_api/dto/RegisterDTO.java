package com.uninassau.livraria_api.dto;

import com.uninassau.livraria_api.entities.UserRole;

public record RegisterDTO(String email, String password, String name, UserRole role) {
}
