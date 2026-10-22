package dev.team.astranova.core.service;

import dev.team.astranova.core.dto.StakeDTO;
import dev.team.astranova.core.entity.Stake;
import dev.team.astranova.core.port.MissionRepository;
import dev.team.astranova.core.port.StakeRepository;
import dev.team.astranova.core.port.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class StakeService {
    private final StakeRepository stakeRepository;
    private final UserRepository userRepository;
    private final MissionRepository missionRepository;

    public StakeService(
            StakeRepository stakeRepository,
            UserRepository userRepository,
            MissionRepository missionRepository
    ) {
        this.stakeRepository = stakeRepository;
        this.userRepository = userRepository;
        this.missionRepository = missionRepository;
    }

    public List<Stake> findAll() {
        return stakeRepository.findAll();
    }

    public Optional<Stake> findById(UUID stakeId) {
        if (stakeId == null) {
            throw new IllegalArgumentException("El id de la participación no puede ser nulo");
        }
        return stakeRepository.findById(stakeId);
    }

    public Stake create(UUID userId, UUID missionId, StakeDTO stakeRequest) {
        if (userId == null) {
            throw new IllegalArgumentException("El id del usuario no puede ser nulo");
        }
        if (missionId == null) {
            throw new IllegalArgumentException("El id de la misión no puede ser nulo");
        }
        if (stakeRequest == null) {
            throw new IllegalArgumentException("La solicitud de participación no puede ser nula");
        }
        if (userRepository.findById(userId).isEmpty()) {
            throw new IllegalArgumentException("No existe el usuario asociado a la participación");
        }
        if (missionRepository.findById(missionId).isEmpty()) {
            throw new IllegalArgumentException("No existe la misión asociada a la participación");
        }
        boolean duplicatedStake = stakeRepository.findAll().stream()
                .anyMatch(stake -> stake.userId().equals(userId) && stake.missionId().equals(missionId));
        if (duplicatedStake) {
            throw new IllegalArgumentException("El usuario ya participa en esa misión");
        }
        return stakeRepository.create(userId, missionId, stakeRequest);
    }

    public Optional<Stake> update(UUID stakeId, StakeDTO stakeRequest) {
        if (stakeId == null) {
            throw new IllegalArgumentException("El id de la participación no puede ser nulo");
        }
        if (stakeRequest == null) {
            throw new IllegalArgumentException("La solicitud de participación no puede ser nula");
        }
        if (stakeRepository.findById(stakeId).isEmpty()) {
            throw new IllegalArgumentException("No existe la participación a actualizar");
        }
        return stakeRepository.update(stakeId, stakeRequest);
    }

    public void delete(UUID stakeId) {
        if (stakeId == null) {
            throw new IllegalArgumentException("El id de la participación no puede ser nulo");
        }
        if (stakeRepository.findById(stakeId).isEmpty()) {
            throw new IllegalArgumentException("No existe la participación a eliminar");
        }
        stakeRepository.delete(stakeId);
    }
}
