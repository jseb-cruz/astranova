package dev.team.astranova.core.port;

import dev.team.astranova.core.dto.UserDTO;
import dev.team.astranova.core.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Port interface defining user persistence operations.
 */
public interface UserRepository {

    // Return all users
    List<User> findAll();

    // Find user by id
    Optional<User> findById(UUID userId);

    // Create a new user from the provided DTO
    User create(UserDTO userRequest);

    // Update an existing user
    User update(UUID userId, UserDTO userRequest);

    // Delete a user by id
    void delete(UUID userId);
}
