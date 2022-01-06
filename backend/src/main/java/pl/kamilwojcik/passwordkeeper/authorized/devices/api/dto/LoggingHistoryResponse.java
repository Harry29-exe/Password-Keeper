package pl.kamilwojcik.passwordkeeper.authorized.devices.api.dto;

import pl.kamilwojcik.passwordkeeper.authorized.devices.dto.LoggingEventDTO;

import java.util.List;

public record LoggingHistoryResponse(
        Integer page,
        Integer availablePages,
        Integer itemsPerPage,
        List<LoggingEventDTO> loggingEvents
) {
}
