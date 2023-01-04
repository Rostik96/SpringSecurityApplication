package com.study.rost.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.study.rost.models.Person;
import com.study.rost.services.ValidationPeopleService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class PersonValidator implements Validator {
    private final ValidationPeopleService validationPeopleService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == Person.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        validationPeopleService.findPersonByUserName(person.getUsername())
                .ifPresent(p -> errors.rejectValue("username", "", "Пользователь с таким именем уже существует."));
    }
}