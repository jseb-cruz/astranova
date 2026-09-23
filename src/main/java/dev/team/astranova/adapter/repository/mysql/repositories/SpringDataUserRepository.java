package dev.team.astranova.adapter.repository.mysql.repositories;

import dev.team.astranova.adapter.repository.mysql.entity.UserJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataUserRepository extends JpaRepository<UserJPA, UUID> {
}
