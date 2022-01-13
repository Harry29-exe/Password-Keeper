package pl.kamilwojcik.passwordkeeper.auth.authorized_devices.services.dto;

public record CreateClientDevice(
        String ipAddress,
        String userAgentHeader,
        String username
) {
}
