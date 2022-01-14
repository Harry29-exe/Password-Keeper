package pl.kamilwojcik.passwordkeeper.config;

import org.springframework.web.bind.annotation.CrossOrigin;

import static pl.kamilwojcik.passwordkeeper.config.consts.CorsAddresses.FRONTEND_ADDRESS;

@CrossOrigin(origins = {FRONTEND_ADDRESS, "https://192.168.0.185:4430"}, allowedHeaders = "Authorization")
public interface CrossOriginController {
}
