package dev.team.astranova.core.entity;

import dev.team.astranova.core.constant.UserRole;

import java.util.UUID;

public record User(
        UUID userId,
        String name,
        String lastName,
        String email,
        String password,
        UserRole role
) {
    public User(UUID userId, String name, String lastName, String email, String password, UserRole role) {
        if (userId == null) {
            throw new IllegalArgumentException("El id del usuario no puede ser nulo");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre del usuario no puede ser nulo o vacio");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("El apellido del usuario no puede ser nulo o vacio");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("El email del usuario no puede ser nulo o vacio");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("La contraseña del usuario no puede ser nula o vacia");
        }
        if (role == null) {
            throw new IllegalArgumentException("El rol del usuario no puede ser nulo");
        }
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
