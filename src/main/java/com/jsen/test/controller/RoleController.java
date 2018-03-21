package com.jsen.test.controller;


import com.jsen.test.entity.User;
import com.jsen.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jsen
 * @since 2018-03-21
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    TestService service;
    @RequestMapping("/test001")
    public List<User> test() {
        return service.getAllUser();
    }

}

