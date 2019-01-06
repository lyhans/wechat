package com.example.controller;

import com.example.form.TestForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/test")
public class JsonController {

    private static final Logger log = LoggerFactory.getLogger(JsonController.class);
//
//    @Autowired
//    private AccountService accountService;
//
//    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
//    public int addUser(Account user){
//        return accountService.addUser(user);
//    }
//
//    @RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
//    public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
//        return accountService.findAllAccount(pageNum,pageSize);
//    }

    @RequestMapping(value="test",produces = MediaType.APPLICATION_XML_VALUE)
    public TestForm test(@RequestBody TestForm testForm){
        log.info(testForm.toString());
        return testForm;
    }

    @RequestMapping(value="testjs")
    public TestForm testjs(@RequestBody TestForm testForm){
        log.info(testForm.toString());
        return testForm;
    }
}