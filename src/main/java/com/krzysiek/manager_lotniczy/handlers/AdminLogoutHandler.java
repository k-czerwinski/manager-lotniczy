package com.krzysiek.manager_lotniczy.handlers;

import com.krzysiek.manager_lotniczy.repository.KlientRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class AdminLogoutHandler implements LogoutHandler {
    @Autowired
    KlientRepository klientRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        klientRepository.ustawRoleUzytkownika();
    }
}
