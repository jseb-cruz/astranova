package dev.team.astranova.core.port;

import dev.team.astranova.core.dto.SpaceshipDTO;
import dev.team.astranova.core.entity.Spaceship;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpaceshipRepository {
    List<Spaceship> findALl();

    Optional<Spaceship> findById(UUID spaceshipId);

    Spaceship create(SpaceshipDTO spaceshipRequest);

    Spaceship update(UUID spaceshipId, SpaceshipDTO spaceshipRequest);

    void delete(UUID spaceshipId);
}
