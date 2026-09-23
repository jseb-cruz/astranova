package dev.team.astranova.adapter.repository.mysql.entity;

import dev.team.astranova.core.constant.UserRole;
import dev.team.astranova.core.entity.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserJPA {

    @Id
    @Column(name = "user_id", nullable = false, updatable = false, columnDefinition = "CHAR(36)")
    private UUID userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private AstronautJPA astronaut;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<StakeJPA> stakes = new ArrayList<>();

    protected UserJPA() {
    }

    public UserJPA(UUID userId, String name, String lastName, String email, String password, UserRole role) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static UserJPA fromDomain(User user) {
        return new UserJPA(
                user.userId(),
                user.name(),
                user.lastName(),
                user.email(),
                user.password(),
                user.role()
        );
    }

    public User toDomain() {
        return new User(userId, name, lastName, email, password, role);
    }

    public UUID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public AstronautJPA getAstronaut() {
        return astronaut;
    }

    public List<StakeJPA> getStakes() {
        return stakes;
    }
}