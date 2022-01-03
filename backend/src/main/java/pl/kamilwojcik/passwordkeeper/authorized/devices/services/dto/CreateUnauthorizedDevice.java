package pl.kamilwojcik.passwordkeeper.authorized.devices.services.dto;

public record CreateUnauthorizedDevice(
        String ipAddress,
        String clientName,
        String username
) {
}
