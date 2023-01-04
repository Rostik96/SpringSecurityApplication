package com.study.rost.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.study.rost.models.Person;
import com.study.rost.repositories.PeopleRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistrationService {
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(Person registered) {
        registered.setPassword(
                passwordEncoder.encode(registered.getPassword())
        );
        peopleRepository.save(registered);
    }
}
