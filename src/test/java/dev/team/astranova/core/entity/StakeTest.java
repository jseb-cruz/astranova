package dev.team.astranova.core.entity;

import dev.team.astranova.core.constant.MissionRole;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class StakeTest{
    private final UUID mockId = UUID.randomUUID();
    private final UUID mockUserId = UUID.randomUUID();
    private final UUID mockMissionId = UUID.randomUUID();
    private final MissionRole mockMissionRole = MissionRole.MROLE_ASTRONAUT;

    @Test
    void ShouldCreateStakeWithValidData(){
        Stake stake = new Stake(mockId, mockUserId, mockMissionId, mockMissionRole);

        assertEquals(mockId, stake.stakeId());
        assertEquals(mockUserId, stake.userId());
        assertEquals(mockMissionId, stake.missionId());
        assertEquals(mockMissionRole, stake.role());
    }

    @Test
    void ShouldRejectNullId(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException
                .class,() -> new Stake(
                        null,
                        mockUserId,
                        mockMissionId,
                        mockMissionRole
                )
        );
        assertEquals("El id de la participación no puede ser nulo",
                exception.getMessage()
        );
    }

    @Test
    void ShouldRejectNullUserId(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException
                .class,() -> new Stake(
                        mockId,
                        null,
                        mockMissionId,
                        mockMissionRole
                )
        );
        assertEquals("El id del usuario no puede ser nulo",
                exception.getMessage()
        );
    }

    @Test
    void ShouldRejectNullMissionId(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException
                .class,() -> new Stake(
                        mockId,
                        mockUserId,
                        null,
                        mockMissionRole
                )
        );
        assertEquals("El id de la mision no puede ser nulo",
                exception.getMessage()
        );
    }

    @Test
    void ShouldRejectNullMissionRole(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException
                .class,() -> new Stake(
                        mockId,
                        mockUserId,
                        mockMissionId,
                        null
                )
        );
        assertEquals("El rol de la participación no puede ser nulo",
                exception.getMessage()
        );
    }
}