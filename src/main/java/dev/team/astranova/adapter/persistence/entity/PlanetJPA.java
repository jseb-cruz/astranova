package dev.team.astranova.adapter.persistence.entity;

import dev.team.astranova.core.constant.EnvironmentalConditions;
import dev.team.astranova.core.constant.ExplorationState;
import dev.team.astranova.core.entity.Planet;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "planets")
public class PlanetJPA {

    @Id
    @Column(name = "planet_id", nullable = false, updatable = false, columnDefinition = "CHAR(36)")
    private UUID planetId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double distance;

    @Column(nullable = false)
    private String type;

    @Column(name = "available_resources", nullable = false)
    private String availableResources;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExplorationState status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnvironmentalConditions conditions;

    @OneToMany(mappedBy = "planet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MissionJPA> missions = new ArrayList<>();

    protected PlanetJPA() {
    }

    public PlanetJPA(UUID planetId, String name, double distance, String type, String availableResources,
                    ExplorationState status, EnvironmentalConditions conditions) {
        this.planetId = planetId;
        this.name = name;
        this.distance = distance;
        this.type = type;
        this.availableResources = availableResources;
        this.status = status;
        this.conditions = conditions;
    }

    public static PlanetJPA fromDomain(Planet planet) {
        return new PlanetJPA(
                planet.planetId(),
                planet.name(),
                planet.distance(),
                planet.type(),
                planet.available_resources(),
                planet.status(),
                planet.conditions()
        );
    }

    public Planet toDomain() {
        return new Planet(planetId, name, distance, type, availableResources, status, conditions);
    }

    public UUID getPlanetId() {
        return planetId;
    }

    public String getName() {
        return name;
    }

    public double getDistance() {
        return distance;
    }

    public String getType() {
        return type;
    }

    public String getAvailableResources() {
        return availableResources;
    }

    public ExplorationState getStatus() {
        return status;
    }

    public EnvironmentalConditions getConditions() {
        return conditions;
    }

    public List<MissionJPA> getMissions() {
        return missions;
    }
}
