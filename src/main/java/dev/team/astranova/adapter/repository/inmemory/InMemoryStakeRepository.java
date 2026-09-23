package dev.team.astranova.adapter.repository.inmemory;

import dev.team.astranova.core.dto.StakeDTO;
import dev.team.astranova.core.entity.Stake;
import dev.team.astranova.core.port.StakeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class InMemoryStakeRepository implements StakeRepository {

    private final ArrayList<Stake> stakes = new ArrayList<>();

    @Override
    public List<Stake> findAll() {
        return stakes;
    }

    @Override
    public Optional<Stake> findById(UUID stakeId) {
        return stakes.stream()
                .filter(stake -> stake.stakeId().equals(stakeId))
                .findFirst();
    }

    @Override
    public Stake create(StakeDTO stakeRequest) {
        Stake stake = new Stake(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                stakeRequest.role()
        );
        stakes.add(stake);
        return stake;
    }

    @Override
    public Stake update(UUID stakeId, StakeDTO stakeRequest) {
        for (Stake stake : stakes) {
            if (stake.stakeId().equals(stakeId)) {
                Stake updatedStake = new Stake(
                        stake.stakeId(),
                        stake.userId(),
                        stake.missionId(),
                        stakeRequest.role()
                );
                stakes.set(stakes.indexOf(stake), updatedStake);
                return updatedStake;
            }
        }
        return null;
    }

    @Override
    public void delete(UUID stakeId) {
        stakes.removeIf(stake -> stake.stakeId().equals(stakeId));
    }
}
