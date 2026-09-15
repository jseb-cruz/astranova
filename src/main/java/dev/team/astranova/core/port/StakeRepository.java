package dev.team.astranova.core.port;

import dev.team.astranova.core.dto.StakeDTO;
import dev.team.astranova.core.entity.Stake;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StakeRepository {

    List<Stake> findAll();

    Optional<Stake> findById(UUID stakeId);

    Stake create(StakeDTO stakeRequest);

    Stake update(UUID stakeId, StakeDTO stakeRequest);

    void delete(UUID stakeId);
}
