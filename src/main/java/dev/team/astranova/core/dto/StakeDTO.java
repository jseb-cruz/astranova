package dev.team.astranova.core.dto;

import dev.team.astranova.core.constant.MissionRole;

public record StakeDTO (
        String userName,
        String missionName,
        MissionRole role
){
}
