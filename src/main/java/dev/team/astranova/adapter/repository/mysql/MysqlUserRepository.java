package dev.team.astranova.adapter.repository.mysql;

import dev.team.astranova.core.dto.UserDTO;
import dev.team.astranova.core.entity.User;
import dev.team.astranova.core.port.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MysqlUserRepository implements UserRepository {
    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public Optional<User> findById(UUID userId) {
        return Optional.empty();
    }

    @Override
    public User create(UserDTO userRequest) {
        return null;
    }

    @Override
    public User update(UUID userId, UserDTO userRequest) {
        return null;
    }

    @Override
    public void delete(UUID userId) {

    }
}
