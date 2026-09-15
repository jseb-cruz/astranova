package dev.team.astranova.adapter.repository.inmemory;

import dev.team.astranova.core.dto.AstronautDTO;
import dev.team.astranova.core.entity.Astronaut;
import dev.team.astranova.core.port.AstronautRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InMemoryAstronautRepository implements AstronautRepository {

    ArrayList<Astronaut> List = new ArrayList<>();
    @Override
    public List<Astronaut> findAll() {
        return List;
    }

    @Override
    public Optional<Astronaut> findById(UUID astronautId) {
        for (Astronaut astronaut : List) {
            if (astronaut.userId().equals(astronautId)) {
                return Optional.of(astronaut);
            }
        }
        return Optional.empty();
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
        List.add(astronaut);
        return astronaut;
    }

    @Override
    public Astronaut update(UUID astronautId, AstronautDTO astronautRequest) {
        for (int i = 0; i < List.size(); i++) {
            if (List.get(i).userId().equals(astronautId)) {
                Astronaut updatedAstronaut = new Astronaut(
                        astronautId,
                        astronautRequest.nationality(),
                        astronautRequest.range(),
                        astronautRequest.specialty(),
                        astronautRequest.experience(),
                        astronautRequest.state()
                );
                List.set(i, updatedAstronaut);
                return updatedAstronaut;
            }
        }
        return null;
    }

    @Override
    public void delete(UUID astronautId) {
        List.removeIf(astronaut -> astronaut.userId().equals(astronautId));
    }
}
