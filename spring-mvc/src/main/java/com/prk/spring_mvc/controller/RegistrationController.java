package com.prk.spring_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class RegistrationController {

    // returns the name of the appropriate jsp page
    @GetMapping("registration")
    public String getRegistration(Map<String, Object> model) {
        return "registration";
    }
}
