package com.jsen.test.controller;

import com.jsen.test.entity.User;
import com.jsen.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestService service;
    @RequestMapping("/test001")
    public List<User> test() {
        return service.getAllUser();
    }
    @RequestMapping("/test002")
    public List<User> test2() {
        service.TestAll();
        return null;
    }
}
