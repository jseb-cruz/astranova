package dev.team.astranova.core.entity;

import dev.team.astranova.core.constant.UserRole;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {

    private final UUID mockId = UUID.randomUUID();
    private final UserRole mockRole = UserRole.ROLE_USER;

    @Test
    void ShouldCreateUserWithValidData() {
        User user = new User(mockId,
                "John",
                "Doe",
                "Johndue@gmail.com",
                "password123",
                mockRole);
        assertEquals(mockId, user.userId());
        assertEquals("John", user.name());
        assertEquals("Doe", user.lastName());
        assertEquals("Johndue@gmail.com", user.email());
        assertEquals("password123", user.password());
        assertEquals(mockRole, user.role());
    }

    @Test
    void ShouldRejectNullId() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException
                .class, () -> new User(
                        null,
                        "John",
                        "Doe",
                        "Johndue@gmail.com",
                        "password123",
                        mockRole));
        assertEquals("El id del usuario no puede ser nulo",
                exception.getMessage());
    }

    @Test
    void ShouldRejectNullName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException
                .class, () -> new User(
                        mockId,
                        null,
                        "Doe",
                        "Johndue@gmail.com",
                        "password123",
                        mockRole));
        assertEquals("El nombre del usuario no puede ser nulo o vacio",
                exception.getMessage());
    }

    @Test
    void ShouldRejectBlankName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException
                .class, () -> new User(
                        mockId,
                        "   ",
                        "Doe",
                        "Johndue@gmail.com",
                        "password123",
                        mockRole));
        assertEquals("El nombre del usuario no puede ser nulo o vacio",
                exception.getMessage());
    }

    @Test
    void ShouldRejectNullLastName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException
                .class, () -> new User(
                        mockId,
                        "John",
                        null,
                        "Johndue@gmail.com",
                        "password123",
                        mockRole));
        assertEquals("El apellido del usuario no puede ser nulo o vacio",
                exception.getMessage());
    }

    @Test
    void ShouldRejectBlankLastName() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException
                .class, () -> new User(
                        mockId,
                        "John",
                        "   ",
                        "Johndue@gmail.com",
                        "password123",
                        mockRole));
        assertEquals("El apellido del usuario no puede ser nulo o vacio",
                exception.getMessage());
    }

    @Test
    void ShouldRejectNullEmail() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException
                .class, () -> new User(
                        mockId,
                        "John",
                        "Doe",
                        null,
                        "password123",
                        mockRole));
        assertEquals("El email del usuario no puede ser nulo o vacio",
                exception.getMessage());
    }

    @Test
    void ShouldRejectBlankEmail() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException
                .class, () -> new User(
                        mockId,
                        "John",
                        "Doe",
                        "   ",
                        "password123",
                        mockRole));
        assertEquals("El email del usuario no puede ser nulo o vacio",
                exception.getMessage());
    }

    @Test
    void ShouldRejectNullPassword() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException
                .class, () -> new User(
                        mockId,
                        "John",
                        "Doe",
                        "Johndue@gmail.com",
                        null,
                        mockRole));
        assertEquals("La contraseña del usuario no puede ser nula o vacia",
                exception.getMessage());
    }

    @Test
    void ShouldRejectBlankPassword() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException
                .class, () -> new User(
                        mockId,
                        "John",
                        "Doe",
                        "Johndue@gmail.com",
                        "   ",
                        mockRole));
        assertEquals("La contraseña del usuario no puede ser nula o vacia",
                exception.getMessage());
    }

    @Test
    void ShouldRejectNullRole() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException
                .class, () -> new User(
                        mockId,
                        "John",
                        "Doe",
                        "Johndue@gmail.com",
                        "password123",
                        null));
        assertEquals("El rol del usuario no puede ser nulo",
                exception.getMessage());
    }

}