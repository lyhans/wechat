package com.example.service.impl;

import com.example.form.UserForm;
import com.example.mapper.UserMapper;
import com.example.model.User;
import com.example.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * @author <a href="mailto:lyhans@yeah.net">Young</a>
 * @version 2019/1/18 0:37
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public Long addUser(UserForm userForm) {
        User user = new User();
        user.setName(userForm.getName());
        return userMapper.insertSelective(user);
    }
}