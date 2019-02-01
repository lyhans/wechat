package com.gladfish.work.pubase.service.impl;

import com.gladfish.work.wechat.form.UserForm;
import com.gladfish.work.pubase.mapper.UserEntityMapper;
import com.gladfish.work.pubase.model.UserEntity;
import com.gladfish.work.pubase.service.IUserService;
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
    private UserEntityMapper userMapper;


    @Override
    public Long addUser(UserForm userForm) {
        UserEntity user = new UserEntity();
        user.setName(userForm.getName());
        return userMapper.insertSelective(user);
    }
}
