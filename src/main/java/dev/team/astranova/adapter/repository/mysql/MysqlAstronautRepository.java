package dev.team.astranova.adapter.repository.mysql;

import dev.team.astranova.adapter.repository.mysql.entity.AstronautJPA;
import dev.team.astranova.adapter.repository.mysql.repositories.SpringDataAstronautRepository;
import dev.team.astranova.core.dto.AstronautDTO;
import dev.team.astranova.core.entity.Astronaut;
import dev.team.astranova.core.port.AstronautRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MysqlAstronautRepository implements AstronautRepository {

    private final SpringDataAstronautRepository springDataAstronautRepository;

    public MysqlAstronautRepository(SpringDataAstronautRepository springDataAstronautRepository) {
        this.springDataAstronautRepository = springDataAstronautRepository;
    }

    // Return all astronauts
    @Override
    public List<Astronaut> findAll() {
        return springDataAstronautRepository.findAll()
                .stream()
                .map(AstronautJPA::toDomain)
                .toList();
    }

    // Find an astronaut by id
    @Override
    public Optional<Astronaut> findById(UUID astronautId) {
        return springDataAstronautRepository.findById(astronautId)
                .map(AstronautJPA::toDomain);
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
        return springDataAstronautRepository.save(AstronautJPA.fromDomain(astronaut)).toDomain();
    }

    // Update existing astronaut; returns empty if not found
    @Override
    public Optional<Astronaut> update(UUID astronautId, AstronautDTO astronautRequest) {
        return springDataAstronautRepository.findById(astronautId)
                .map(existing -> {
                    Astronaut updatedAstronaut = new Astronaut(
                            existing.getUserId(),
                            astronautRequest.nationality(),
                            astronautRequest.range(),
                            astronautRequest.specialty(),
                            astronautRequest.experience(),
                            astronautRequest.state()
                    );
                    return springDataAstronautRepository.save(AstronautJPA.fromDomain(updatedAstronaut)).toDomain();
                });
    }

    // Delete astronaut by id
    @Override
    public void delete(UUID astronautId) {
        springDataAstronautRepository.deleteById(astronautId);
    }
}