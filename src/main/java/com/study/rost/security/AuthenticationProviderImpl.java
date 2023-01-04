package com.study.rost.security;

import java.security.Principal;
import java.util.Collections;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.study.rost.services.PersonDetailsService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {
    private final PersonDetailsService personDetailsService;

    /**
     * Spring сам передаёт данные (логин/пароль) из Web-формы аутентификации в этот метод.
     *
     * @param authentication содержит credentials {@link Authentication#getCredentials()},
     *                       которые будут сравниваться с теми, что лежат в БД.
     * @return объект {@link Authentication}, который уже содержит объект {@link Principal}
     *
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails personDetails = personDetailsService.loadUserByUsername(username);

        if (!password.equals(personDetails.getPassword()))
            throw new BadCredentialsException("Incorrect password");

        return new UsernamePasswordAuthenticationToken(personDetails, password, Collections.emptyList());
    }

    /**
     * Нужен Spring, чтобы понять, для какого именно объекта authentication он будет работает.
     * Даёт возможность не использовать AuthenticationProvider там, на что он не рассчитан.
     *
     * @param authentication объект для верификации
     * @return результат верификации
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
