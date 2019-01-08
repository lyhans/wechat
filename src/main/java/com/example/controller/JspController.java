package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2019/1/8 23:27
 */
@Controller
@RequestMapping(value = "/tjsp")
public class JspController {

    @RequestMapping(value = "/test")
    public String test(){
        return "test";
    }
}
