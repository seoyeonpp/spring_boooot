package com.example.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/hello")

@Controller
public class HelloController {

    @GetMapping("")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "/hello";
    }

    @GetMapping("/mvc")
    public String helloMvc(@RequestParam(value = "name", defaultValue = "") String name, Model model) {
        model.addAttribute("name",name);
        return "/hello-template";
    }
}
