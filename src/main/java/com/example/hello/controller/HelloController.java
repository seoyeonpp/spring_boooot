package com.example.hello.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/string")
    @ResponseBody //http에서 body 부분에 이 내용을 직접 넣어주겠다.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

//    api방식은 객체를 반환하는것 / view 없이 http response에다가 값을 딱 넣어주는것
    @GetMapping("/api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    @Getter
    @Setter
    static class Hello {
        private String name;
    }
}
