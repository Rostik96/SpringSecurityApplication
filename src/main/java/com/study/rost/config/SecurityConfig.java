package com.study.rost.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter { //Главный класс, где мы настраиваем Spring Security.
    private final AuthenticationProvider authenticationProviderImpl;

    //Настраивает аутентификацию
    @Override
    protected void configure(AuthenticationManagerBuilder authentication) {
        authentication.authenticationProvider(authenticationProviderImpl); //TODO have to autowire {@link AuthenticationProvider}
    }
}
