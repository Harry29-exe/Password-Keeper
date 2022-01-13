package pl.kamilwojcik.passwordkeeper.config;

import org.springframework.web.bind.annotation.CrossOrigin;

import static pl.kamilwojcik.passwordkeeper.config.consts.CorsAddresses.FRONTEND_ADDRESS;

@CrossOrigin(origins = FRONTEND_ADDRESS, allowedHeaders = "Authorization")
public interface CrossOriginController {
}
