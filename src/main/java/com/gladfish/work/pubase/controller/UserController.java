package com.gladfish.work.pubase.controller;

import com.gladfish.work.wechat.form.UserForm;
import com.gladfish.work.pubase.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2019/1/18 0:34
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

//    @RequestMapping("add.json")
//    public Long add(UserForm userForm){
//        return userService.addUser(userForm);
//    }
}
