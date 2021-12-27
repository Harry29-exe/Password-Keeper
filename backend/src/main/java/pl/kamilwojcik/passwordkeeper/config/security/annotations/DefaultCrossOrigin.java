package pl.kamilwojcik.passwordkeeper.config.security.annotations;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = {"Authorized"})
public @interface DefaultCrossOrigin {
}
