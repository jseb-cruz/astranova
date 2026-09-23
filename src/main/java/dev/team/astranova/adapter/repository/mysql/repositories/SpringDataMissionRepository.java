package dev.team.astranova.adapter.repository.mysql.repositories;

import dev.team.astranova.adapter.repository.mysql.entity.MissionJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataMissionRepository extends JpaRepository<MissionJPA, UUID> {
}
