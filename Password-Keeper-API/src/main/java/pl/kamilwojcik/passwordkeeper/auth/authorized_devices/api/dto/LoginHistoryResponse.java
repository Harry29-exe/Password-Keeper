package pl.kamilwojcik.passwordkeeper.auth.authorized_devices.api.dto;

import pl.kamilwojcik.passwordkeeper.auth.loggingEvent.dto.LoginEventDTO;

import java.util.List;

public record LoginHistoryResponse(
        Integer page,
        Integer availablePages,
        Integer itemsPerPage,
        List<LoginEventDTO> loginEvents
) {
}
