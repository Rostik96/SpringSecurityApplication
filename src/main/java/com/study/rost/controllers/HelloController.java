package com.study.rost.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.study.rost.security.PersonDetails;
import com.study.rost.services.AdminService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class HelloController {
    private final AdminService adminService;

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/show-user-info")
    public ModelAndView showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        System.out.println(personDetails.getPerson());
        return new ModelAndView("userInfo", "principal", personDetails.getPerson());
    }

    @GetMapping("/admin")
    public ModelAndView adminPage() {
        return new ModelAndView("admin", "adminStuff", adminService.getAdminStuff());
    }
}
