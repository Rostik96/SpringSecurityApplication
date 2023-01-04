package com.study.rost.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter { //Главный класс, где мы настраиваем Spring Security
    //Настраивает аутентификацию
    @Override
    protected void configure(AuthenticationManagerBuilder authentication) {
        authentication.authenticationProvider(null); //TODO have to autowire {@link AuthenticationProvider}
    }
}
