package dev.team.astranova.core.port;

import dev.team.astranova.core.entity.Mission;
import dev.team.astranova.core.entity.User;

import java.util.List;
import java.util.UUID;

public interface ReportGateway {
    byte[] generateMissionReport(Mission mission);

    byte[] generateAstronautsHistoryReport(List<User> users);

    byte[] generateMissionHistoryByPlanetReport(UUID planetId);

    byte[] generatePlanetResourcesReport(UUID planetId);

    byte[] generateSpaceshipInventoryReport();
}
