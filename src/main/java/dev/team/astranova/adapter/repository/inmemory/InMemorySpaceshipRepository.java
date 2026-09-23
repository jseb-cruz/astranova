package dev.team.astranova.adapter.repository.inmemory;

import dev.team.astranova.core.dto.SpaceshipDTO;
import dev.team.astranova.core.entity.Spaceship;
import dev.team.astranova.core.port.SpaceshipRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InMemorySpaceshipRepository implements SpaceshipRepository {

    private final ArrayList<Spaceship> spaceships = new ArrayList<>();

    @Override
    public List<Spaceship> findAll() {
        return spaceships;
    }

    @Override
    public Optional<Spaceship> findById(UUID spaceshipId) {
        return spaceships.stream()
                .filter(spaceship -> spaceship.spaceshipId().equals(spaceshipId))
                .findFirst();
    }

    @Override
    public Spaceship create(SpaceshipDTO spaceshipRequest) {
        Spaceship spaceship = new Spaceship(
                UUID.randomUUID(),
                spaceshipRequest.name(),
                spaceshipRequest.model(),
                spaceshipRequest.capacity(),
                spaceshipRequest.status(),
                spaceshipRequest.last_date_maintenance(),
                spaceshipRequest.autonomy(),
                spaceshipRequest.fabrication_date()
        );
        spaceships.add(spaceship);
        return spaceship;
    }

    @Override
    public Optional<Spaceship> update(UUID spaceshipId, SpaceshipDTO spaceshipRequest) {
        for (Spaceship spaceship : spaceships) {
            if (spaceship.spaceshipId().equals(spaceshipId)) {
                Spaceship updatedSpaceship = new Spaceship(
                        spaceshipId,
                        spaceshipRequest.name(),
                        spaceshipRequest.model(),
                        spaceshipRequest.capacity(),
                        spaceshipRequest.status(),
                        spaceshipRequest.last_date_maintenance(),
                        spaceshipRequest.autonomy(),
                        spaceshipRequest.fabrication_date()
                );
                spaceships.set(spaceships.indexOf(spaceship), updatedSpaceship);
                return Optional.of(updatedSpaceship);
            }
        }
        return Optional.empty();
    }

    @Override
    public void delete(UUID spaceshipId) {
        spaceships.removeIf(spaceship -> spaceship.spaceshipId().equals(spaceshipId));
    }
}
