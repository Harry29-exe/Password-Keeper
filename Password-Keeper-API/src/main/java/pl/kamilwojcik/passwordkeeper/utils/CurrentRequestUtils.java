package pl.kamilwojcik.passwordkeeper.utils;

import org.springframework.lang.Nullable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


public class CurrentRequestUtils {

    public static HttpServletRequest getCurrentRequest() {
        var requestAttribs = RequestContextHolder.getRequestAttributes();
        if (requestAttribs instanceof ServletRequestAttributes servletAttribs) {
            return servletAttribs.getRequest();
        } else {
            throw new IllegalStateException();
        }
    }

    @Nullable
    public static String getPreProxyIp() {
        return getCurrentRequest().getHeader("X-Real-IP");
    }

    @Nullable
    public static String getHeader(String header) {
        return getCurrentRequest().getHeader(header);
    }


}
