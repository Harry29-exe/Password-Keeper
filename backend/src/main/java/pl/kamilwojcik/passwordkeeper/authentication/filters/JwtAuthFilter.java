package pl.kamilwojcik.passwordkeeper.authentication.filters;

import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.kamilwojcik.passwordkeeper.authentication.services.JwtService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static pl.kamilwojcik.passwordkeeper.authentication.filters.JwtAuthFilter.FILTER_ORDER;

@Component
@Order(0)
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public static final Integer FILTER_ORDER = 1;

    public JwtAuthFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authToken = request.getHeader("Authorization");
        if(authToken == null || authToken.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        var validJwt = jwtService.validateAuthToken(authToken);
        var user = userDetailsService.loadUserByUsername(validJwt.getSubject());
        Authentication auth = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword(),
                //todo
                new ArrayList<>()
        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }

//    @Bean
//    public FilterRegistrationBean<JwtAuthFilter> registrationBean() {
//        FilterRegistrationBean<JwtAuthFilter> registrationBean =
//                new FilterRegistrationBean<>();
//
//
//    }

}
