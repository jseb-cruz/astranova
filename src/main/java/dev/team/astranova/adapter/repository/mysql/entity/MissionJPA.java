package dev.team.astranova.adapter.repository.mysql.entity;

import dev.team.astranova.core.constant.MissionState;
import dev.team.astranova.core.entity.Mission;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "missions")
public class MissionJPA {

    @Id
    @Column(name = "mission_id", nullable = false, updatable = false, columnDefinition = "CHAR(36)")
    private UUID missionId;

    @Column(name = "spaceship_id", nullable = false, insertable = false, updatable = false, columnDefinition = "CHAR(36)")
    private UUID spaceshipId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spaceship_id", referencedColumnName = "spaceship_id", nullable = false)
    private SpaceshipJPA spaceship;

    @Column(name = "planet_id", nullable = false, insertable = false, updatable = false, columnDefinition = "CHAR(36)")
    private UUID planetId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planet_id", referencedColumnName = "planet_id", nullable = false)
    private PlanetJPA planet;

    @Column(nullable = false)
    private String name;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(nullable = false)
    private String objective;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MissionState state;

    @Column(nullable = false)
    private String description;

    @Column(name = "used_recurses", nullable = false)
    private String usedRecurses;

    @Column(nullable = false)
    private String result;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<StakeJPA> stakes = new ArrayList<>();

    protected MissionJPA() {
    }

    public MissionJPA(UUID missionId, UUID spaceshipId, UUID planetId, String name, LocalDate startDate,
                     LocalDate endDate, String objective, MissionState state, String description,
                     String usedRecurses, String result) {
        this.missionId = missionId;
        this.spaceshipId = spaceshipId;
        this.planetId = planetId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.objective = objective;
        this.state = state;
        this.description = description;
        this.usedRecurses = usedRecurses;
        this.result = result;
    }

    public static MissionJPA fromDomain(Mission mission) {
        return new MissionJPA(
                mission.missionId(),
                mission.spaceshipId(),
                mission.planetId(),
                mission.name(),
                mission.startDate(),
                mission.endDate(),
                mission.objective(),
                mission.state(),
                mission.description(),
                mission.usedRecurses(),
                mission.result()
        );
    }

    public Mission toDomain() {
        UUID spaceshipId = spaceship != null ? spaceship.getSpaceshipId() : null;
        UUID planetId = planet != null ? planet.getPlanetId() : null;
        return new Mission(missionId, spaceshipId, planetId, name, startDate, endDate, objective, state, description, usedRecurses, result);
    }

    public UUID getMissionId() {
        return missionId;
    }

    public SpaceshipJPA getSpaceship() {
        return spaceship;
    }

    public PlanetJPA getPlanet() {
        return planet;
    }

    public UUID getSpaceshipId() {
        return spaceshipId;
    }

    public UUID getPlanetId() {
        return planetId;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getObjective() {
        return objective;
    }

    public MissionState getState() {
        return state;
    }

    public String getDescription() {
        return description;
    }

    public String getUsedRecurses() {
        return usedRecurses;
    }

    public String getResult() {
        return result;
    }

    public List<StakeJPA> getStakes() {
        return stakes;
    }
}
