package dev.team.astranova.adapter.repository.mysql;

import dev.team.astranova.adapter.repository.mysql.entity.UserJPA;
import dev.team.astranova.adapter.repository.mysql.repositories.SpringDataUserRepository;
import dev.team.astranova.core.dto.UserDTO;
import dev.team.astranova.core.entity.User;
import dev.team.astranova.core.port.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MysqlUserRepository implements UserRepository {

    private final SpringDataUserRepository springDataUserRepository;

    public MysqlUserRepository(SpringDataUserRepository springDataUserRepository) {
        this.springDataUserRepository = springDataUserRepository;
    }

    // Return all users
    @Override
    public List<User> findAll() {
        return springDataUserRepository.findAll()
                .stream()
                .map(UserJPA::toDomain)
                .toList();
    }

    // Find a user by id
    @Override
    public Optional<User> findById(UUID userId) {
        return springDataUserRepository.findById(userId)
                .map(UserJPA::toDomain);
    }

    // Create a new user from a DTO
    @Override
    public User create(UserDTO userRequest) {
        User user = new User(
                UUID.randomUUID(),
                userRequest.name(),
                userRequest.lastName(),
                userRequest.email(),
                userRequest.password(),
                userRequest.role()
        );
        return springDataUserRepository.save(UserJPA.fromDomain(user)).toDomain();
    }

    // Update existing user; returns empty if not found
    @Override
    public Optional<User> update(UUID userId, UserDTO userRequest) {
        return springDataUserRepository.findById(userId)
                .map(existing -> {
                    User updatedUser = new User(
                            existing.getUserId(),
                            userRequest.name(),
                            userRequest.lastName(),
                            userRequest.email(),
                            userRequest.password(),
                            userRequest.role()
                    );
                    return springDataUserRepository.save(UserJPA.fromDomain(updatedUser)).toDomain();
                });
    }

    // Delete user by id
    @Override
    public void delete(UUID userId) {
        springDataUserRepository.deleteById(userId);
    }
}