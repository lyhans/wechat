package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {
    @RequestMapping("/test")
    public ModelAndView test(ModelAndView modelAndView) {
        System.out.println("test");
//        modelAndView.setViewName("test");
        return modelAndView;
    }

    @RequestMapping("/test2")
    public String test2(Model Mode) {
        System.out.println("test2");
        return "test2";
    }
}