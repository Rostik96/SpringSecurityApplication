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
     * @return объект {@link Authentication}, который уже будет содержать объект {@link Principal} и
     *         который будет помещён в сессию данного principal'a (пользователя), предосталяя нам доступ
     *         к его {@link PersonDetails}.
     *
     * @throws AuthenticationException в случае ошибки в рамках аутентификации
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails personDetails = personDetailsService.loadUserByUsername(username);

        //TODO Пока храним пароли в незашифрованном виде, что неправильно ==> добавить шифрование.
        if (!password.equals(personDetails.getPassword()))
            throw new BadCredentialsException("Incorrect password"); //Будет отображено в Web-форме by Spring

        return new UsernamePasswordAuthenticationToken(personDetails, password, Collections.emptyList());
    }

    /**
     * Нужен Spring'у, чтобы понять, для какого именно объекта authentication данный provider будет работает.
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
