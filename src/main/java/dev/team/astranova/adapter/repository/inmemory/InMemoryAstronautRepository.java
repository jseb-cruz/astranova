package dev.team.astranova.adapter.repository.inmemory;

import dev.team.astranova.core.dto.AstronautDTO;
import dev.team.astranova.core.entity.Astronaut;
import dev.team.astranova.core.port.AstronautRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * In-memory astronaut repository. Stores astronauts in an internal list for testing.
 */
public class InMemoryAstronautRepository implements AstronautRepository {

    // Internal storage list
    private final ArrayList<Astronaut> astronauts = new ArrayList<>();

    // Return all astronauts (internal list reference)
    @Override
    public List<Astronaut> findAll() {
        return astronauts;
    }

    // Find an astronaut by id
    @Override
    public Optional<Astronaut> findById(UUID astronautId) {
        return astronauts.stream()
                .filter(astronaut -> astronaut.userId().equals(astronautId))
                .findFirst();
    }

    // Create a new astronaut linked to an existing user id
    @Override
    public Astronaut create(UUID userId, AstronautDTO astronautRequest) {
        Astronaut astronaut = new Astronaut(
                userId,
                astronautRequest.nationality(),
                astronautRequest.range(),
                astronautRequest.specialty(),
                astronautRequest.experience(),
                astronautRequest.state()
        );
        astronauts.add(astronaut);
        return astronaut;
    }

    // Update existing astronaut; returns empty if not found
    @Override
    public Optional<Astronaut> update(UUID astronautId, AstronautDTO astronautRequest) {
        for (Astronaut astronaut : astronauts) {
            if (astronaut.userId().equals(astronautId)) {
                Astronaut updatedAstronaut = new Astronaut(
                        astronaut.userId(),
                        astronautRequest.nationality(),
                        astronautRequest.range(),
                        astronautRequest.specialty(),
                        astronautRequest.experience(),
                        astronautRequest.state()
                );
                astronauts.set(astronauts.indexOf(astronaut), updatedAstronaut);
                return Optional.of(updatedAstronaut);
            }
        }
        return Optional.empty();
    }

    // Delete astronaut by id
    @Override
    public void delete(UUID astronautId) {
        astronauts.removeIf(a -> a.userId().equals(astronautId));
    }
}