package com.example.service.impl;

import com.example.mapper.AccountMapper;
import com.example.model.Account;
import com.example.service.AccountService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;//这里会报错，但是并不会影响

    @Override
    public int addUser(Account account) {

        return accountMapper.insertSelective(account);
    }

    /*
    * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
    * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
    * pageNum 开始页数
    * pageSize 每页显示的数据条数
    * */
    @Override
    public List<Account> findAllAccount(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        return accountMapper.selectAllAccount();
    }
}