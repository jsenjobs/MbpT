package com.jsen.test.utils.controller;

import com.alibaba.fastjson.JSONObject;
import com.jsen.test.Boot;
import com.jsen.test.service.AccountService;
import com.jsen.test.utils.help.ProfilesResolver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Boot.class)                   //加载应用程序上下文
@WebAppConfiguration
@ActiveProfiles(resolver = ProfilesResolver.class)
public class ValidatorTestTest {

    @Autowired
    private WebApplicationContext webContext;   //注入WebApplicationContext
    private MockMvc mockMvc;
    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webContext)        //设置MockMvc
                .build();
    }

    @Test
    public void pPerson() throws Exception {
        mockMvc.perform(get("/validator/person").param("name", "jsen").param("dead", "true").param("password", "123456"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"code\":1, \"msg\":\"dead必须为False\"}"));

        mockMvc.perform(get("/validator/person").param("dead", "false").param("password", "123456"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"code\":1, \"msg\":\"姓名不能为空\"}"));

        mockMvc.perform(get("/validator/person").param("name", "jsen").param("dead", "false").param("password", "12456"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"code\":1, \"msg\":\"密码长度不能小于6位\"}"));

        mockMvc.perform(get("/validator/person").param("name", "jsen").param("dead", "false"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"code\":1, \"msg\":\"密码不能为空\"}"));
    }

    @Autowired
    AccountService accountService;

    @Test
    public void accountTest() {
        JSONObject jsonObject = accountService.login("jsen", "123456");
        assert jsonObject.getInteger("code") == 1;
    }
}
