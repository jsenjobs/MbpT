package com.jsen.test.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jsen.test.entity.Account;
import com.jsen.test.mapper.AccountMapper;
import com.jsen.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    AccountMapper mapper;
    @Override
    public List<Account> getAllUser() {
        return mapper.selectList(new EntityWrapper<>());
    }

    @Override
    public void TestAll() {
        Account u = new Account();
        u.setId(1);
        System.out.println(mapper.selectById(u.getId()));
        u.setId(6);u.setName("Jacxk");u.setSex("nv");
        mapper.insert(u);
        mapper.delete(new EntityWrapper<Account>().eq("name", "jsen"));

        System.out.println(mapper.selectPage(new Page<Account>(1, 10), new EntityWrapper<Account>().eq("name", "Jacxk")).size());

    }


}
