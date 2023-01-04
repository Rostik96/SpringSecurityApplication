package com.study.rost.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.study.rost.models.Person;
import com.study.rost.repositories.PeopleRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ValidationPeopleService {
    private final PeopleRepository peopleRepository;

    public Optional<Person> findPersonByUserName(String username) {
        return peopleRepository.findByUsername(username);
    }

    public List<Person> index() {
        return peopleRepository.findAll();
    }
}
