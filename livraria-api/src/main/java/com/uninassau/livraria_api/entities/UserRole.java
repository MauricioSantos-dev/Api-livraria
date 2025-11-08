package com.uninassau.livraria_api.entities;

public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");
    private String role;
    UserRole(String role){
        this.role=role;
    }
    public String getRole(){
        return this.role;
    }
}
