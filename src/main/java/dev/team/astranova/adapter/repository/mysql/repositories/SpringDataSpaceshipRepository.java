package dev.team.astranova.adapter.repository.mysql.repositories;

import dev.team.astranova.adapter.repository.mysql.entity.SpaceshipJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataSpaceshipRepository extends JpaRepository<SpaceshipJPA, UUID> {
}
