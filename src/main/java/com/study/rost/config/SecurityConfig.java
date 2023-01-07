package com.study.rost.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.study.rost.services.PersonDetailsService;
import lombok.AllArgsConstructor;

import static com.study.rost.security.Role.*;

@AllArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PersonDetailsService personDetailsService;

    /**
     * В данном методе конфигурируем сам Spring Security & авторизацию.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/auth/login", "/auth/registration", "/error").permitAll()
                    .anyRequest().hasAnyRole(ROLE_ADMIN.roleName(), ROLE_USER.roleName())
                .and()
                    .formLogin()
                    .loginPage("/auth/login")
                    .loginProcessingUrl("/process_login") //Security будет ждать логин/пароль по данному адресу.
                    .defaultSuccessUrl("/hello", true)
                    .failureUrl("/auth/login?error")
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/auth/login");
    }

    //Настраиваем логику аутентификацию
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
