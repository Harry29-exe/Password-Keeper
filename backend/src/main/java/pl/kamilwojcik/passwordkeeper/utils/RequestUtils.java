package pl.kamilwojcik.passwordkeeper.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


public class RequestUtils {

    public static HttpServletRequest getRequest() {
        var requestAttribs = RequestContextHolder.getRequestAttributes();
        if (requestAttribs instanceof ServletRequestAttributes servletAttribs) {
            return servletAttribs.getRequest();
        } else {
            throw new IllegalStateException();
        }
    }


}
