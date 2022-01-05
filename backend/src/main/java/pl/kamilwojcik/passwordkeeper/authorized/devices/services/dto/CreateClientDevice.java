package pl.kamilwojcik.passwordkeeper.authorized.devices.services.dto;

public record CreateClientDevice(
        String ipAddress,
        String userAgentHeader,
        String username
) {
}
