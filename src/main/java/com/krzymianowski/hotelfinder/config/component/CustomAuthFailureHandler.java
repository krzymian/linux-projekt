package com.krzymianowski.hotelfinder.config.component;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        String referer = request.getHeader("Referer");
        if (referer.indexOf("?") > 0) referer = referer.substring(0, referer.indexOf("?"));
        getRedirectStrategy().sendRedirect(request, response, referer +"?error");
    }
}