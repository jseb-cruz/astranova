package dev.team.astranova.adapter.repository.inmemory;

import dev.team.astranova.core.dto.UserDTO;
import dev.team.astranova.core.entity.User;
import dev.team.astranova.core.port.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * In-memory user repository. Stores users in an internal list for testing.
 */
public class InMemoryUserRepository implements UserRepository {

    // Internal storage list
    private final ArrayList<User> users = new ArrayList<>();

    // Return all users (internal list reference)
    @Override
    public List<User> findAll() {
        return users;
    }

    // Find a user by id
    @Override
    public Optional<User> findById(UUID userId) {
        return users.stream()
                .filter(user -> user.userId() == userId)
                .findFirst();
    }

    // Create a new user from a DTO
    @Override
    public User create(UserDTO userRequest) {
        User user = new User(UUID.randomUUID(), userRequest.name(), userRequest.lastName(), userRequest.email(), userRequest.password(), userRequest.role());
        users.add(user);
        return user;
    }

    // Update existing user; returns updated user or null if not found
    @Override
    public Optional<User> update(UUID userId, UserDTO userRequest) {
        for (User user : users) {
            if (user.userId().equals(userId)) {
                User updatedUser = new User(
                        user.userId(),
                        userRequest.name(),
                        userRequest.lastName(),
                        userRequest.email(),
                        userRequest.password(),
                        userRequest.role()
                );
                users.set(users.indexOf(user), updatedUser);
                return Optional.of(updatedUser);
            }
        }
        return Optional.empty();
    }

    // Delete user by id
    @Override
    public void delete(UUID userId) {
        users.removeIf(u -> u.userId().equals(userId));
    }
}

