package com.study.rost.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @PreAuthorize("hasAnyRole()")
    public String getAdminStuff() {
        return "ADMIN stuff";
    }
}
