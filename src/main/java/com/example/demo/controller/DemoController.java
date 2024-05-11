package com.example.demo.controller;

import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class DemoController {

    @GetMapping("/hello")
    public String hello() {

        return "hello world !!";
    }

}
