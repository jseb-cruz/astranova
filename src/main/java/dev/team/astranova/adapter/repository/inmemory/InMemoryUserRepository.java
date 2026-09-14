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
    private final ArrayList<User> List = new ArrayList<>();

    // Return all users (internal list reference)
    @Override
    public List<User> findAll() {
        return List;
    }

    // Find a user by id
    @Override
    public Optional<User> findById(UUID UserId) {
        for (User user : List) {
            if (user.userId().equals(UserId)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    // Create a new user from a DTO
    @Override
    public User create(UserDTO userRequest) {
        User user = new User(UUID.randomUUID(), userRequest.name(), userRequest.lastName(), userRequest.email(), userRequest.password(), userRequest.role());
        List.add(user);
        return user;
    }

    // Update existing user; returns updated user or null if not found
    @Override
    public User update(UUID userId, UserDTO userRequest) {
        for (int i = 0; i < List.size(); i++) {
            User existing = List.get(i);
            if (existing.userId().equals(userId)) {
                User updated = new User(userId, userRequest.name(), userRequest.lastName(), userRequest.email(), userRequest.password(), userRequest.role());
                List.set(i, updated);
                return updated;
            }
        }
        return null;
    }

    // Delete user by id
    @Override
    public void delete(UUID id) {
        List.removeIf(u -> u.userId().equals(id));
    }
}

