package com.jsen.test.service;

import com.jsen.test.Boot;
import com.jsen.test.utils.help.ProfilesResolverLocal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Boot.class)                   //加载应用程序上下文
@ActiveProfiles(resolver = ProfilesResolverLocal.class)
public class DatacontainerServiceTest {

    @Test
    public void insertBatch() {
    }

    @Test
    public void insertBatch2() {
    }

    @Test
    public void insertBatch3() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void transTimeout() {
    }

    @Test
    public void transReadOnly() {
    }

    @Test
    public void transNoRollBackException() {
    }
}