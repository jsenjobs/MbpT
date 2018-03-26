package com.jsen.test.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jsen.test.entity.Account;
import com.jsen.test.mapper.AccountMapper;
import com.jsen.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service
public class TestServiceImpl implements TestService {
    @Autowired
    AccountMapper mapper;
    @Override
    public List<Account> getAllUser() {
        return mapper.selectList(new EntityWrapper<>());
    }
    @Override
    @Transactional
    public Map<String, Integer> TestAll() {
        Map<String, Integer> result = new HashMap<>();
        // ADD
        Account u = new Account();
        u.setName("jack");
        u.setSex("nv");
        result.put("insert", mapper.insert(u));

        // DELETE
        result.put("delete", mapper.delete(new EntityWrapper<Account>().eq("name", "jsen")));

        if (true)
            throw new RuntimeException("Test Exception");
        u = new Account();
        u.setId(5);
        u.setName("jacc");
        result.put("update", mapper.updateById(u));
        u.setName("lucy");
        result.put("update2", mapper.update(u, new EntityWrapper<Account>().eq("name", "jacc")));

        result.put("list", mapper.selectPage(new Page<Account>(1, 10), new EntityWrapper<Account>().eq("name", "lucy")).size());

        return result;
    }


}
