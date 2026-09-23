package dev.team.astranova.adapter.repository.inmemory;

import dev.team.astranova.core.dto.StakeDTO;
import dev.team.astranova.core.entity.Stake;
import dev.team.astranova.core.port.StakeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * In-memory stake repository. Stores stakes in an internal list for testing.
 */
public class InMemoryStakeRepository implements StakeRepository {

    // Internal storage list
    private final ArrayList<Stake> stakes = new ArrayList<>();

    // Return all stakes (internal list reference)
    @Override
    public List<Stake> findAll() {
        return stakes;
    }

    // Find a stake by id
    @Override
    public Optional<Stake> findById(UUID stakeId) {
        return stakes.stream()
                .filter(stake -> stake.stakeId().equals(stakeId))
                .findFirst();
    }

    // Create a new stake linked to an existing user and mission
    @Override
    public Stake create(UUID userId, UUID missionId, StakeDTO stakeRequest) {
        Stake stake = new Stake(
                UUID.randomUUID(),
                userId,
                missionId,
                stakeRequest.role()
        );
        stakes.add(stake);
        return stake;
    }

    // Update existing stake; returns empty if not found
    @Override
    public Optional<Stake> update(UUID stakeId, StakeDTO stakeRequest) {
        for (Stake stake : stakes) {
            if (stake.stakeId().equals(stakeId)) {
                Stake updatedStake = new Stake(
                        stake.stakeId(),
                        stake.userId(),
                        stake.missionId(),
                        stakeRequest.role()
                );
                stakes.set(stakes.indexOf(stake), updatedStake);
                return Optional.of(updatedStake);
            }
        }
        return Optional.empty();
    }

    // Delete stake by id
    @Override
    public void delete(UUID stakeId) {
        stakes.removeIf(s -> s.stakeId().equals(stakeId));
    }
}