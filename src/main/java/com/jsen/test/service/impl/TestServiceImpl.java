package com.jsen.test.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jsen.test.entity.User;
import com.jsen.test.mapper.UserMapper;
import com.jsen.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    UserMapper mapper;
    @Override
    public List<User> getAllUser() {
        return mapper.selectList(new EntityWrapper<>());
    }

    @Override
    public void TestAll() {
        User u = new User();
        u.setId(1);
        System.out.println(mapper.selectById(u.getId()));
        u.setId(6);u.setName("Jacxk");u.setSex("NV");
        mapper.insert(u);
        mapper.delete(new EntityWrapper<User>().eq("name", "jsen"));

        System.out.println(mapper.selectPage(new Page<User>(1, 10), new EntityWrapper<User>().eq("name", "Jacxk")).size());

    }


}
