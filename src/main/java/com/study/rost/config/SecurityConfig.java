package com.study.rost.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.study.rost.services.PersonDetailsService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //private final AuthenticationProvider authenticationProviderImpl;

    //Настраивает аутентификацию
    /*@Override
    protected void configure(AuthenticationManagerBuilder authentication) {
        authentication.authenticationProvider(authenticationProviderImpl);
    }*/

    private final PersonDetailsService personDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService);
    }

    /**
     * Пока что не используем шифрование паролей.
     * TODO: исправить в дальнейшем
     * @return заглушка для шифратора паролей
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
