package com.study.rost.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.study.rost.models.Person;
import com.study.rost.repositories.PeopleRepository;
import com.study.rost.security.AuthenticationProviderImpl;
import com.study.rost.security.PersonDetails;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PersonDetailsService implements UserDetailsService {
    private final PeopleRepository peopleRepository;

    /**
     * Получаем объект {@link Person}, который содержит в т.ч. логин({@link Person#username})/пароль({@link Person#password}),
     * для оборачивания его в {@link PersonDetails} и передачи в {@link AuthenticationProviderImpl#authenticate}.
     *
     * @param username ключ для поиска в БД
     * @return объект с Credentials внутри
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new PersonDetails(
                peopleRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found!")) ////Будет отображено в Web-форме by Spring
        );
    }
}
