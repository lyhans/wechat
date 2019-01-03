package com.example.service;

import com.example.model.Account;

import java.util.List;

public interface AccountService {

    int addUser(Account user);

    List<Account> findAllAccount(int pageNum, int pageSize);
}