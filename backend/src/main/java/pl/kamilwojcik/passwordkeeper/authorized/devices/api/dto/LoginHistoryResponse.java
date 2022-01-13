package pl.kamilwojcik.passwordkeeper.authorized.devices.api.dto;

import pl.kamilwojcik.passwordkeeper.authorized.loggingEvent.dto.LoginEventDTO;

import java.util.List;

public record LoginHistoryResponse(
        Integer page,
        Integer availablePages,
        Integer itemsPerPage,
        List<LoginEventDTO> loginEvents
) {
}
