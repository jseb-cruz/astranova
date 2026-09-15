package dev.team.astranova.adapter.repository.inmemory;

import dev.team.astranova.core.dto.StakeDTO;
import dev.team.astranova.core.entity.Stake;
import dev.team.astranova.core.port.StakeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InMemoryStakeRepository implements StakeRepository {

    private final ArrayList<Stake> List = new ArrayList<>();

    @Override
    public List<Stake> findAll() {
        return List;
    }

    @Override
    public Optional<Stake> findById(UUID stakeId) {
        for (Stake stake : List) {
            if (stake.stakeId().equals(stakeId)) {
                return Optional.of(stake);
            }
        }
        return Optional.empty();
    }

    @Override
    public Stake create(StakeDTO stakeRequest) {
        Stake stake = new Stake(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                stakeRequest.role()
        );
        List.add(stake);
        return stake;
    }

    @Override
    public Stake update(UUID stakeId, StakeDTO stakeRequest) {
        for (int i = 0; i < List.size(); i++) {
            Stake existing = List.get(i);
            if (existing.stakeId().equals(stakeId)) {
                Stake updatedStake = new Stake(
                        existing.stakeId(),
                        existing.userId(),
                        existing.missionId(),
                        stakeRequest.role()
                );
                List.set(i, updatedStake);
                return updatedStake;
            }
        }
        return null;
    }

    @Override
    public void delete(UUID stakeId) {
        List.removeIf(stake -> stake.stakeId().equals(stakeId));
    }
}
