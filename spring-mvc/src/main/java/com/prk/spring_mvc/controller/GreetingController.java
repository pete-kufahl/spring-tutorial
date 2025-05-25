package com.prk.spring_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class GreetingController {

    // returns the name of the appropriate jsp page
    @GetMapping("greeting")
    public String greeting(Map<String, Object> model) {
        System.out.println("Greeting endpoint was hit");
        model.put("message", "hello my client");
        return "greeting";
    }
}
