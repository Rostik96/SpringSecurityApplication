package com.study.rost.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.study.rost.models.Person;
import lombok.AllArgsConstructor;

/**
 * Класс-обёртка над аутентифицируемой сущностью для единообразия получения её учётных данных и
 * системной информации по этим credentials.
 */

@AllArgsConstructor
public class PersonDetails implements UserDetails {
    private final Person person;

    /**
     * Необходимо для получения аутентифицированного пользователя.
     *
     * @return principal
     */
    public Person getPerson() {
        return person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //Могли бы быть действия, как то: SHOW_ACCOUNT, WITHDRAW_MONEY, SEND_MONEY
        return Collections.singletonList(new SimpleGrantedAuthority(person.getRole().name()));
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public String getUsername() {
        return person.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
