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

    @RequestMapping(value="test.json")
    public TestForm test(){
        log.info("test.json");
        TestForm testForm = new TestForm();
        testForm.setName("1111");
        testForm.setCount(235);
        testForm.setNum(11);
        return testForm;
    }

}