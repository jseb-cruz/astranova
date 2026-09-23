package dev.team.astranova.adapter.repository.mysql;

import dev.team.astranova.adapter.repository.mysql.entity.StakeJPA;
import dev.team.astranova.adapter.repository.mysql.repositories.SpringDataStakeRepository;
import dev.team.astranova.core.dto.StakeDTO;
import dev.team.astranova.core.entity.Stake;
import dev.team.astranova.core.port.StakeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MysqlStakeRepository implements StakeRepository {

    private final SpringDataStakeRepository springDataStakeRepository;

    public MysqlStakeRepository(SpringDataStakeRepository springDataStakeRepository) {
        this.springDataStakeRepository = springDataStakeRepository;
    }

    // Return all stakes
    @Override
    public List<Stake> findAll() {
        return springDataStakeRepository.findAll()
                .stream()
                .map(StakeJPA::toDomain)
                .toList();
    }

    // Find a stake by id
    @Override
    public Optional<Stake> findById(UUID stakeId) {
        return springDataStakeRepository.findById(stakeId)
                .map(StakeJPA::toDomain);
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
        return springDataStakeRepository.save(StakeJPA.fromDomain(stake)).toDomain();
    }

    // Update existing stake; returns empty if not found
    @Override
    public Optional<Stake> update(UUID stakeId, StakeDTO stakeRequest) {
        return springDataStakeRepository.findById(stakeId)
                .map(existing -> {
                    Stake updatedStake = new Stake(
                            existing.getStakeId(),
                            existing.getUserId(),
                            existing.getMissionId(),
                            stakeRequest.role()
                    );
                    return springDataStakeRepository.save(StakeJPA.fromDomain(updatedStake)).toDomain();
                });
    }

    // Delete stake by id
    @Override
    public void delete(UUID stakeId) {
        springDataStakeRepository.deleteById(stakeId);
    }
}