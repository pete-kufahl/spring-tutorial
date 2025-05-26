package com.prk.spring_mvc.controller;

import com.prk.spring_mvc.model.Registration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    // returns the name of the appropriate jsp page
    @GetMapping("registration")
    public String getRegistration(@ModelAttribute("registration")Registration registration) {
        return "registration";
    }

    @PostMapping("registration")
    public String addRegistration(@ModelAttribute("registration")Registration registration) {
        System.out.println("Registration: " + registration.getName());
        // enable post-redirect-get pattern to prevent form resubmit
        //  input textbox is cleared after submit is pressed
        return "redirect:registration";
    }

}
