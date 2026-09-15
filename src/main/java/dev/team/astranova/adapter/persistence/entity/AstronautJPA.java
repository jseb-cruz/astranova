package dev.team.astranova.adapter.persistence.entity;

import dev.team.astranova.core.constant.AstronautState;
import dev.team.astranova.core.entity.Astronaut;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "astronauts")
public class AstronautJPA {

    @Id
    @Column(name = "user_id", nullable = false, updatable = false, columnDefinition = "CHAR(36)")
    private UUID userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserJPA user;

    @Column(nullable = false)
    private String nationality;

    @Column(name = "astronaut_rank", nullable = false)
    private String range;

    @Column(nullable = false)
    private String specialty;

    @Column(nullable = false)
    private String experience;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AstronautState status;

    protected AstronautJPA() {
    }

    public AstronautJPA(UUID userId, String nationality, String range, String specialty, String experience, AstronautState status) {
        this.userId = userId;
        this.nationality = nationality;
        this.range = range;
        this.specialty = specialty;
        this.experience = experience;
        this.status = status;
    }

    public static AstronautJPA fromDomain(Astronaut astronaut) {
        return new AstronautJPA(
                astronaut.userId(),
                astronaut.nationality(),
                astronaut.range(),
                astronaut.specialty(),
                astronaut.experience(),
                astronaut.status()
        );
    }

    public Astronaut toDomain() {
        return new Astronaut(userId, nationality, range, specialty, experience, status);
    }

    public UUID getUserId() {
        return userId;
    }

    public UserJPA getUser() {
        return user;
    }

    public String getNationality() {
        return nationality;
    }

    public String getRange() {
        return range;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getExperience() {
        return experience;
    }

    public AstronautState getStatus() {
        return status;
    }
}
