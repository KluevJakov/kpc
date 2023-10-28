package com.example.kpc.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@SuppressWarnings("unused")
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final CustomAuthProvider customAuthProvider;

    @Autowired
    public SecurityConfiguration(CustomAuthProvider customAuthProvider) {
        this.customAuthProvider = customAuthProvider;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder managerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        managerBuilder.authenticationProvider(customAuthProvider);
        return managerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/css/**").permitAll()
                    .requestMatchers("/img/**").permitAll()
                    .requestMatchers("/js/**").permitAll()
                    .requestMatchers("/api/**").permitAll()
                    .requestMatchers("/files/**").permitAll()
                    .requestMatchers("/upload/**").permitAll()
                    .requestMatchers("/qr").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(e -> {
                e.loginPage("/")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/successLogin", true)
                .usernameParameter("email")
                .permitAll();
            })
            .cors(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .logout(LogoutConfigurer::permitAll);
        return http.build();
    }



}
