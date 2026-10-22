package dev.team.astranova.core.service;

import dev.team.astranova.adapter.config.InMemoryConfig;
import dev.team.astranova.core.constant.*;
import dev.team.astranova.core.dto.*;
import dev.team.astranova.core.entity.*;
import dev.team.astranova.core.port.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ServiceBusinessRulesTest {

    private UserService userService;
    private AstronautService astronautService;
    private MissionService missionService;
    private StakeService stakeService;
    private SpaceshipRepository spaceshipRepository;
    private PlanetRepository planetRepository;
    private UserRepository userRepository;
    private AstronautRepository astronautRepository;
    private MissionRepository missionRepository;
    private StakeRepository stakeRepository;

    @BeforeEach
    void setUp() {
        InMemoryConfig config = new InMemoryConfig();
        userRepository = config.userRepository();
        astronautRepository = config.astronautRepository();
        missionRepository = config.missionRepository();
        planetRepository = config.planetRepository();
        spaceshipRepository = config.spaceshipRepository();
        stakeRepository = config.stakeRepository();

        userService = config.userService(userRepository);
        astronautService = config.astronautService(astronautRepository, userRepository);
        missionService = config.missionService(missionRepository, spaceshipRepository, planetRepository);
        stakeService = config.stakeService(stakeRepository, userRepository, missionRepository);
    }

    @Test
    void shouldRejectDuplicateUserEmail() {
        UserDTO dto = new UserDTO("Ana", "Diaz", "ana@test.com", "1234", UserRole.ROLE_USER);
        userService.create(dto);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> userService.create(dto));

        assertEquals("Ya existe un usuario con ese email", exception.getMessage());
    }

    @Test
    void shouldRejectAstronautForUnknownUser() {
        AstronautDTO dto = new AstronautDTO("Colombia", "Commander", "Pilot", "5 years", AstronautState.ASTATE_ACTIVE);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> astronautService.create(UUID.randomUUID(), dto));

        assertEquals("No existe el usuario asociado al astronauta", exception.getMessage());
    }

    @Test
    void shouldRejectMissionForUnknownPlanet() {
        User user = userRepository.create(new UserDTO("Tom", "Crew", "tom@agency.com", "1234", UserRole.ROLE_ADMIN));
        Spaceship spaceship = spaceshipRepository.create(new SpaceshipDTO("Nave X", "NX-1", 8, SpaceshipState.SSTATE_ACTIVE, LocalDate.now().minusYears(1), 120, LocalDate.now().minusYears(2)));

        MissionDTO missionDTO = new MissionDTO(
                "Mision 1",
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                "Explorar", 
                MissionState.MSTATE_PLANNED,
                "desc",
                "oxygen",
                "success"
        );

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> missionService.create(spaceship.spaceshipId(), UUID.randomUUID(), missionDTO));

        assertEquals("No existe el planeta asociado a la misión", exception.getMessage());
    }

    @Test
    void shouldRejectDuplicateUserMissionStake() {
        User user = userRepository.create(new UserDTO("Luis", "Paz", "luis@agency.com", "1234", UserRole.ROLE_USER));
        Planet planet = planetRepository.create(new PlanetDTO("Mars", 225D, "Terrestrial", "iron", ExplorationState.ESTATE_EXPLORED, EnvironmentalConditions.CONDITIONS_NORMAL));
        Spaceship spaceship = spaceshipRepository.create(new SpaceshipDTO("Nave Z", "NZ-1", 6, SpaceshipState.SSTATE_ACTIVE, LocalDate.now(), 200, LocalDate.now().minusYears(1)));
        Mission mission = missionRepository.create(spaceship.spaceshipId(), planet.planetId(), new MissionDTO(
                "Mission A",
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                "Survey",
                MissionState.MSTATE_PLANNED,
                "desc",
                "resources",
                "pending"
        ));

        StakeDTO stakeDTO = new StakeDTO(MissionRole.MROLE_ASTRONAUT);
        stakeService.create(user.userId(), mission.missionId(), stakeDTO);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> stakeService.create(user.userId(), mission.missionId(), stakeDTO));

        assertEquals("El usuario ya participa en esa misión", exception.getMessage());
    }
}
