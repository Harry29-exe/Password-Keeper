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
        var ip = getCurrentRequest().getHeader("X-Real-IP");
        System.out.println("\n\n" + ip + "\n\n");


        return ip;
    }

    @Nullable
    public static String getHeader(String header) {
        return getCurrentRequest().getHeader(header);
    }


}
