package dev.team.astranova.core.port;

import dev.team.astranova.core.dto.StakeDTO;
import dev.team.astranova.core.entity.Stake;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StakeRepository {

    List<Stake> findAll();
    Optional<Stake> findById(UUID id);
    Stake create(StakeDTO stakeRequest);
    Stake update(UUID id, StakeDTO stakeRequest);
    void delete(UUID id);
}
