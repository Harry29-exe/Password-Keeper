package pl.kamilwojcik.passwordkeeper.config.security.honeypots.filters;

import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.kamilwojcik.passwordkeeper.config.security.honeypots.HoneypotActionType;
import pl.kamilwojcik.passwordkeeper.config.security.honeypots.HoneypotsAccountList;
import pl.kamilwojcik.passwordkeeper.config.security.honeypots.HoneypotsMsgDispatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static pl.kamilwojcik.passwordkeeper.config.security.honeypots.filters.HoneypotsLoggerFilter.FILTER_ORDER;

@Component
@Order(FILTER_ORDER)
public class HoneypotsLoggerFilter extends OncePerRequestFilter {

    public static final Integer FILTER_ORDER = Integer.MAX_VALUE;

    private final HoneypotsAccountList honeypotsAccountList;
    private final HoneypotsMsgDispatcher honeypotsMsgDispatcher;

    public HoneypotsLoggerFilter(HoneypotsAccountList honeypotsAccountList, HoneypotsMsgDispatcher honeypotsMsgDispatcher) {
        this.honeypotsAccountList = honeypotsAccountList;
        this.honeypotsMsgDispatcher = honeypotsMsgDispatcher;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if(honeypotsAccountList.isHoneypot(auth)) {

            honeypotsMsgDispatcher.dispatchMsg(
                    auth,
                    HoneypotActionType.ACCESSING_ENDPOINT);

        }

        filterChain.doFilter(request, response);
    }

}
