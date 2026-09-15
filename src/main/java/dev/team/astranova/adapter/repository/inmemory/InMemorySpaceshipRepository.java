package dev.team.astranova.adapter.repository.inmemory;

import dev.team.astranova.core.dto.SpaceshipDTO;
import dev.team.astranova.core.entity.Spaceship;
import dev.team.astranova.core.port.SpaceshipRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InMemorySpaceshipRepository implements SpaceshipRepository {

    private final ArrayList<Spaceship> List = new ArrayList<>();

    @Override
    public List<Spaceship> findALl() {
        return List;
    }

    @Override
    public Optional<Spaceship> findById(UUID spaceshipId) {
        for (Spaceship spaceship : List) {
            if (spaceship.spaceshipId().equals(spaceshipId)) {
                return Optional.of(spaceship);
            }
        }
        return Optional.empty();
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
        List.add(spaceship);
        return spaceship;
    }

    @Override
    public Spaceship update(UUID spaceshipId, SpaceshipDTO spaceshipRequest) {
        for (int i = 0; i < List.size(); i++) {
            if (List.get(i).spaceshipId().equals(spaceshipId)) {
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
                List.set(i, updatedSpaceship);
                return updatedSpaceship;
            }
        }
        return null;
    }

    @Override
    public void delete(UUID spaceshipId) {
        List.removeIf(spaceship -> spaceship.spaceshipId().equals(spaceshipId));
    }
}
