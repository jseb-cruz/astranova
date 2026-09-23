package dev.team.astranova.adapter.repository.mysql.repositories;

import dev.team.astranova.adapter.repository.mysql.entity.StakeJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataStakeRepository extends JpaRepository<StakeJPA, UUID> {
}
