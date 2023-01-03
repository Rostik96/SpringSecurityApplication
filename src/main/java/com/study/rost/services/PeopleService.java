package com.study.rost.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.study.rost.models.Person;
import com.study.rost.repositories.PeopleRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PeopleService {
    private final PeopleRepository peopleRepository;

    public List<Person> index() {
        return peopleRepository.findAll();
    }
}
