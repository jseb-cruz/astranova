package dev.team.astranova.core.service;

import dev.team.astranova.core.dto.UserDTO;
import dev.team.astranova.core.entity.User;
import dev.team.astranova.core.port.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(UUID userId) {
        if (userId == null) {
            throw new IllegalArgumentException("El id del usuario no puede ser nulo");
        }
        return userRepository.findById(userId);
    }

    public User create(UserDTO userRequest) {
        if (userRequest == null) {
            throw new IllegalArgumentException("La solicitud de usuario no puede ser nula");
        }
        boolean emailAlreadyExists = userRepository.findAll().stream()
                .anyMatch(user -> user.email().equalsIgnoreCase(userRequest.email()));
        if (emailAlreadyExists) {
            throw new IllegalArgumentException("Ya existe un usuario con ese email");
        }
        return userRepository.create(userRequest);
    }

    public Optional<User> update(UUID userId, UserDTO userRequest) {
        if (userId == null) {
            throw new IllegalArgumentException("El id del usuario no puede ser nulo");
        }
        if (userRequest == null) {
            throw new IllegalArgumentException("La solicitud de usuario no puede ser nula");
        }
        if (userRepository.findById(userId).isEmpty()) {
            throw new IllegalArgumentException("No existe el usuario a actualizar");
        }

        boolean emailAlreadyExists = userRepository.findAll().stream()
                .anyMatch(user -> !user.userId().equals(userId) && user.email().equalsIgnoreCase(userRequest.email()));
        if (emailAlreadyExists) {
            throw new IllegalArgumentException("Ya existe otro usuario con ese email");
        }
        return userRepository.update(userId, userRequest);
    }

    public void delete(UUID userId) {
        if (userId == null) {
            throw new IllegalArgumentException("El id del usuario no puede ser nulo");
        }
        if (userRepository.findById(userId).isEmpty()) {
            throw new IllegalArgumentException("No existe el usuario a eliminar");
        }
        userRepository.delete(userId);
    }
}
