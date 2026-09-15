package dev.team.astranova.adapter.persistence.entity;

import dev.team.astranova.core.constant.MissionRole;
import dev.team.astranova.core.entity.Stake;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "stakes")
public class StakeJPA {

    @Id
    @Column(name = "stake_id", nullable = false, updatable = false, columnDefinition = "CHAR(36)")
    private UUID stakeId;

    @Column(name = "user_id", nullable = false, insertable = false, updatable = false, columnDefinition = "CHAR(36)")
    private UUID userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserJPA user;

    @Column(name = "mission_id", nullable = false, insertable = false, updatable = false, columnDefinition = "CHAR(36)")
    private UUID missionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", referencedColumnName = "mission_id", nullable = false)
    private MissionJPA mission;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MissionRole role;

    protected StakeJPA() {
    }

    public StakeJPA(UUID stakeId, UUID userId, UUID missionId, MissionRole role) {
        this.stakeId = stakeId;
        this.userId = userId;
        this.missionId = missionId;
        this.role = role;
    }

    public static StakeJPA fromDomain(Stake stake) {
        return new StakeJPA(
                stake.stakeId(),
                stake.userId(),
                stake.missionId(),
                stake.role()
        );
    }

    public Stake toDomain() {
        return new Stake(stakeId, userId, missionId, role);
    }

    public UUID getStakeId() {
        return stakeId;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getMissionId() {
        return missionId;
    }

    public MissionRole getRole() {
        return role;
    }

    public UserJPA getUser() {
        return user;
    }

    public MissionJPA getMission() {
        return mission;
    }
}
