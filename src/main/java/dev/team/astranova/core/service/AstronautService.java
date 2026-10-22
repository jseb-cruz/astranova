package dev.team.astranova.core.service;

import dev.team.astranova.core.dto.AstronautDTO;
import dev.team.astranova.core.entity.Astronaut;
import dev.team.astranova.core.port.AstronautRepository;
import dev.team.astranova.core.port.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AstronautService {
    private final AstronautRepository astronautRepository;
    private final UserRepository userRepository;

    public AstronautService(AstronautRepository astronautRepository, UserRepository userRepository) {
        this.astronautRepository = astronautRepository;
        this.userRepository = userRepository;
    }

    public List<Astronaut> findAll() {
        return astronautRepository.findAll();
    }

    public Optional<Astronaut> findById(UUID astronautId) {
        if (astronautId == null) {
            throw new IllegalArgumentException("El id del astronauta no puede ser nulo");
        }
        return astronautRepository.findById(astronautId);
    }

    public Astronaut create(UUID userId, AstronautDTO astronautRequest) {
        if (userId == null) {
            throw new IllegalArgumentException("El id del usuario no puede ser nulo");
        }
        if (astronautRequest == null) {
            throw new IllegalArgumentException("La solicitud de astronauta no puede ser nula");
        }
        if (userRepository.findById(userId).isEmpty()) {
            throw new IllegalArgumentException("No existe el usuario asociado al astronauta");
        }
        boolean astronautAlreadyExists = astronautRepository.findById(userId).isPresent();
        if (astronautAlreadyExists) {
            throw new IllegalArgumentException("Ya existe un astronauta para este usuario");
        }
        return astronautRepository.create(userId, astronautRequest);
    }

    public Optional<Astronaut> update(UUID astronautId, AstronautDTO astronautRequest) {
        if (astronautId == null) {
            throw new IllegalArgumentException("El id del astronauta no puede ser nulo");
        }
        if (astronautRequest == null) {
            throw new IllegalArgumentException("La solicitud de astronauta no puede ser nula");
        }
        if (astronautRepository.findById(astronautId).isEmpty()) {
            throw new IllegalArgumentException("No existe el astronauta a actualizar");
        }
        return astronautRepository.update(astronautId, astronautRequest);
    }

    public void delete(UUID astronautId) {
        if (astronautId == null) {
            throw new IllegalArgumentException("El id del astronauta no puede ser nulo");
        }
        if (astronautRepository.findById(astronautId).isEmpty()) {
            throw new IllegalArgumentException("No existe el astronauta a eliminar");
        }
        astronautRepository.delete(astronautId);
    }
}
