package dev.team.astranova.core.entity;

import dev.team.astranova.core.constant.MissionRole;

import java.util.UUID;

public record Stake (
        UUID stakeId,
        UUID userId,
        UUID missionId,
        MissionRole role
){
    public Stake(UUID stakeId, UUID userId, UUID missionId, MissionRole role) {
        if (stakeId == null) {
            throw new IllegalArgumentException("El id del stake no puede ser nulo");
        }
        if (userId == null) {
            throw new IllegalArgumentException("El id del usuario no puede ser nulo");
        }
        if (missionId == null) {
            throw new IllegalArgumentException("El id de la mision no puede ser nulo");
        }
        if (role == null) {
            throw new IllegalArgumentException("El rol del stake no puede ser nulo");
        }
        this.stakeId = stakeId;
        this.userId = userId;
        this.missionId = missionId;
        this.role = role;
    }
}
