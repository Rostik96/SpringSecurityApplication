package com.study.rost.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.rost.dto.PersonDTO;
import com.study.rost.models.Person;
import com.study.rost.services.RegistrationService;
import com.study.rost.util.JwtUtil;
import com.study.rost.util.PersonValidator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final RegistrationService registrationService;
    private final PersonValidator personValidator;
    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("person") Person person) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public Map<String, String> registration(@RequestBody PersonDTO personDTO, BindingResult bindingResult) {
        Person person = convertToPerson(personDTO);
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors())
            return Map.of("message", "Error!"); //TODO: добавить @ExceptionHandler метод

        registrationService.register(person);
        String token = jwtUtil.generateToken(person.getUsername());
        return Map.of("jwt-token", token);
    }

    private Person convertToPerson(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }
}
