package jamos.back.global.exception.interceptor;

import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

public class CookieInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        addSameSite(response, "None");
        return true;
    }

    private void addSameSite(HttpServletResponse response, String sameSite) {
        Collection<String> headers = response.getHeaders(HttpHeaders.SET_COOKIE);
        boolean isFirstHeader = true;
        for (String header : headers) {
            if (isFirstHeader) {
                response.setHeader(HttpHeaders.SET_COOKIE, String.format("%s;Secure;%s", header, "SameSite=" + sameSite));
                isFirstHeader = false;
                continue;
            }
            response.addHeader(HttpHeaders.SET_COOKIE, String.format("%s;Secure;%s", header, "SameSite=" + sameSite));
        }
    }
}
