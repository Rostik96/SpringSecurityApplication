package com.study.rost.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.rost.models.Person;
import com.study.rost.services.RegistrationService;
import com.study.rost.util.PersonValidator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/auth")
public class AuthenticationController {
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
    public String registration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors())
            return "/auth/registration";

        registrationService.register(person);
        return "redirect:/auth/login";
    }
}
