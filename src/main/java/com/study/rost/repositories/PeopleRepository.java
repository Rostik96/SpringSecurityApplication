package com.study.rost.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.rost.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
