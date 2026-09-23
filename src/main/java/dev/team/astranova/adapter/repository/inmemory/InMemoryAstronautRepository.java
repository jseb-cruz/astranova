package dev.team.astranova.adapter.repository.inmemory;

import dev.team.astranova.core.dto.AstronautDTO;
import dev.team.astranova.core.entity.Astronaut;
import dev.team.astranova.core.port.AstronautRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InMemoryAstronautRepository implements AstronautRepository {

    private final ArrayList<Astronaut> astronauts = new ArrayList<>();

    @Override
    public List<Astronaut> findAll() {
        return astronauts;
    }

    @Override
    public Optional<Astronaut> findById(UUID astronautId) {
        return astronauts.stream()
                .filter(astronaut -> astronaut.userId().equals(astronautId))
                .findFirst();
    }

    @Override
    public Astronaut create(AstronautDTO astronautRequest) {
        Astronaut astronaut = new Astronaut(
                UUID.randomUUID(),
                astronautRequest.nationality(),
                astronautRequest.range(),
                astronautRequest.specialty(),
                astronautRequest.experience(),
                astronautRequest.state()
        );
        astronauts.add(astronaut);
        return astronaut;
    }

    @Override
    public Astronaut update(UUID astronautId, AstronautDTO astronautRequest) {
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
                return updatedAstronaut;
            }
        }
        return null;
    }

    @Override
    public void delete(UUID astronautId) {
        astronauts.removeIf(astronaut -> astronaut.userId().equals(astronautId));
    }
}
