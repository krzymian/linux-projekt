package com.krzymianowski.hotelfinder.config.component;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        if(defaultSavedRequest != null){
            String targetURL = defaultSavedRequest.getRedirectUrl();
            getRedirectStrategy().sendRedirect(request, response, targetURL);
        }else {
            String referer = request.getHeader("Referer");
            if (referer != null && referer.indexOf("?") > 0) referer = referer.substring(0, referer.indexOf("?"));
            getRedirectStrategy().sendRedirect(request, response, referer);
        }
        this.handle(request, response, authentication);
        this.clearAuthenticationAttributes(request);
    }

}
