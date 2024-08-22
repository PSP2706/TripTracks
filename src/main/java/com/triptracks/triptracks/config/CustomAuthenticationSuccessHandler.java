package com.triptracks.triptracks.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String role = authentication.getAuthorities().stream()
            .findFirst()
            .map(grantedAuthority -> grantedAuthority.getAuthority())
            .orElse("USER");

        String redirectUrl = "/";

//        if ("ROLE_ADMIN".equals(role)) {
//            redirectUrl = "/admin/home";
//        } else if ("ROLE_CUSTOMER".equals(role)) {
//            redirectUrl = "/customer/home";
//        }

        response.sendRedirect(redirectUrl);
    }
}
