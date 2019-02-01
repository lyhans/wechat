package com.gladfish.work.pubase.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2019/1/21 23:20
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping("test.json")
    public String test(){
        return "success";
    }

}
