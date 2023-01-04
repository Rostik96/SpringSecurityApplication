package com.study.rost.services;

import org.springframework.stereotype.Service;

import com.study.rost.models.Person;
import com.study.rost.repositories.PeopleRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistrationService {
    private final PeopleRepository peopleRepository;

    public void register(Person registered) {
        peopleRepository.save(registered);
    }
}
