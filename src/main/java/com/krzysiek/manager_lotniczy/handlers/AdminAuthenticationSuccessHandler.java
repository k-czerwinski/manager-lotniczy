package com.krzysiek.manager_lotniczy.handlers;

import com.krzysiek.manager_lotniczy.repository.AdminRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AdminAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private AdminRepository adminRepository;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public AdminAuthenticationSuccessHandler(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        adminRepository.ustawRoleAdmina();
        redirectStrategy.sendRedirect(request, response, "/admin/");
    }
}
