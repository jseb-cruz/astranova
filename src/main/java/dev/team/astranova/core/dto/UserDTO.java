package dev.team.astranova.core.dto;

import dev.team.astranova.core.constant.UserRole;

/**
 * DTO representing user data.
 * Password is included for requests but must never be returned in responses.
 */
public record UserDTO(
        String name,
        String lastName,
        String email,
        String password,
        UserRole role) {
}
