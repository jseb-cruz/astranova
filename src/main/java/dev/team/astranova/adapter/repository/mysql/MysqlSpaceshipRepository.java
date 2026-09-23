package dev.team.astranova.adapter.repository.mysql;

import dev.team.astranova.adapter.repository.mysql.entity.SpaceshipJPA;
import dev.team.astranova.adapter.repository.mysql.repositories.SpringDataSpaceshipRepository;
import dev.team.astranova.core.dto.SpaceshipDTO;
import dev.team.astranova.core.entity.Spaceship;
import dev.team.astranova.core.port.SpaceshipRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MysqlSpaceshipRepository implements SpaceshipRepository {

    private final SpringDataSpaceshipRepository springDataSpaceshipRepository;

    public MysqlSpaceshipRepository(SpringDataSpaceshipRepository springDataSpaceshipRepository) {
        this.springDataSpaceshipRepository = springDataSpaceshipRepository;
    }

    // Return all spaceships
    @Override
    public List<Spaceship> findAll() {
        return springDataSpaceshipRepository.findAll()
                .stream()
                .map(SpaceshipJPA::toDomain)
                .toList();
    }

    // Find a spaceship by id
    @Override
    public Optional<Spaceship> findById(UUID spaceshipId) {
        return springDataSpaceshipRepository.findById(spaceshipId)
                .map(SpaceshipJPA::toDomain);
    }

    // Create a new spaceship from a DTO
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
        return springDataSpaceshipRepository.save(SpaceshipJPA.fromDomain(spaceship)).toDomain();
    }

    // Update existing spaceship; returns empty if not found
    @Override
    public Optional<Spaceship> update(UUID spaceshipId, SpaceshipDTO spaceshipRequest) {
        return springDataSpaceshipRepository.findById(spaceshipId)
                .map(existing -> {
                    Spaceship updatedSpaceship = new Spaceship(
                            existing.getSpaceshipId(),
                            spaceshipRequest.name(),
                            spaceshipRequest.model(),
                            spaceshipRequest.capacity(),
                            spaceshipRequest.status(),
                            spaceshipRequest.last_date_maintenance(),
                            spaceshipRequest.autonomy(),
                            spaceshipRequest.fabrication_date()
                    );
                    return springDataSpaceshipRepository.save(SpaceshipJPA.fromDomain(updatedSpaceship)).toDomain();
                });
    }

    // Delete spaceship by id
    @Override
    public void delete(UUID spaceshipId) {
        springDataSpaceshipRepository.deleteById(spaceshipId);
    }
}