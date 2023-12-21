package com.krzysiek.manager_lotniczy.configuration;

import com.krzysiek.manager_lotniczy.handlers.AdminAuthenticationSuccessHandler;
import com.krzysiek.manager_lotniczy.handlers.AdminLogoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    AdminAuthenticationSuccessHandler adminAuthenticationSuccessHandler;

    @Autowired
    AdminLogoutHandler adminLogoutHandler;

    public WebSecurityConfig(AdminAuthenticationSuccessHandler adminAuthenticationSuccessHandler, AdminLogoutHandler adminLogoutHandler) {
        this.adminAuthenticationSuccessHandler = adminAuthenticationSuccessHandler;
        this.adminLogoutHandler = adminLogoutHandler;
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/js/**", "/css/**", "/webjars/**").permitAll()
                        .requestMatchers("/client/**", "/client/", "/login").anonymous()
                        .requestMatchers("/logout").authenticated()
                        .requestMatchers("/admin/**", "/admin/").hasRole("ADMIN")
                ).formLogin((formLogin) -> formLogin
                        .successHandler(adminAuthenticationSuccessHandler)
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .addLogoutHandler(adminLogoutHandler)
                        .logoutSuccessUrl("/client/"))
                .build();
    }
}
