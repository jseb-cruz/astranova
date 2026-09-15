package dev.team.astranova.adapter.persistence.entity;

import dev.team.astranova.core.constant.SpaceshipState;
import dev.team.astranova.core.entity.Spaceship;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "spaceships")
public class SpaceshipJPA {

    @Id
    @Column(name = "spaceship_id", nullable = false, updatable = false, columnDefinition = "CHAR(36)")
    private UUID spaceshipId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int capacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SpaceshipState status;

    @Column(name = "last_date_maintenance")
    private LocalDate lastDateMaintenance;

    @Column(nullable = false)
    private int autonomy;

    @Column(name = "fabrication_date")
    private LocalDate fabricationDate;

    @OneToMany(mappedBy = "spaceship", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MissionJPA> missions = new ArrayList<>();

    protected SpaceshipJPA() {
    }

    public SpaceshipJPA(UUID spaceshipId, String name, String model, int capacity, SpaceshipState status,
                       LocalDate lastDateMaintenance, int autonomy, LocalDate fabricationDate) {
        this.spaceshipId = spaceshipId;
        this.name = name;
        this.model = model;
        this.capacity = capacity;
        this.status = status;
        this.lastDateMaintenance = lastDateMaintenance;
        this.autonomy = autonomy;
        this.fabricationDate = fabricationDate;
    }

    public static SpaceshipJPA fromDomain(Spaceship spaceship) {
        return new SpaceshipJPA(
                spaceship.spaceshipId(),
                spaceship.name(),
                spaceship.model(),
                spaceship.capacity(),
                spaceship.status(),
                spaceship.last_date_maintenance(),
                spaceship.autonomy(),
                spaceship.fabrication_date()
        );
    }

    public Spaceship toDomain() {
        return new Spaceship(spaceshipId, name, model, capacity, status, lastDateMaintenance, autonomy, fabricationDate);
    }

    public UUID getSpaceshipId() {
        return spaceshipId;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public int getCapacity() {
        return capacity;
    }

    public SpaceshipState getStatus() {
        return status;
    }

    public LocalDate getLastDateMaintenance() {
        return lastDateMaintenance;
    }

    public int getAutonomy() {
        return autonomy;
    }

    public LocalDate getFabricationDate() {
        return fabricationDate;
    }

    public List<MissionJPA> getMissions() {
        return missions;
    }
}
