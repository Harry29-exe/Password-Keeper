package pl.kamilwojcik.passwordkeeper.config;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

//@Component
public class TestFilter extends OncePerRequestFilter {

    private final Logger logger = Logger.getLogger(TestFilter.class.getName());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " \n" + request.getPathInfo());
    }
}
