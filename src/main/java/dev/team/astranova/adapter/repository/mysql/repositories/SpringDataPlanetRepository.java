package dev.team.astranova.adapter.repository.mysql.repositories;

import dev.team.astranova.adapter.repository.mysql.entity.PlanetJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataPlanetRepository extends JpaRepository<PlanetJPA, UUID> {
}
