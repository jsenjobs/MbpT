package com.jsen.test.utils.mapper;

import com.jsen.test.Boot;
import com.jsen.test.mapper.AccountMapper;
import com.jsen.test.utils.help.ProfilesResolverLocal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/3
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Boot.class)                   //加载应用程序上下文
@ActiveProfiles(resolver = ProfilesResolverLocal.class)
public class AccountTest {
    @Autowired
    AccountMapper accountMapper;

    @Test
    public void listAccountJoinWeibos() {
        assert accountMapper.listAccountJoinWeibos().size() > 0;
    }

    @Test
    public void listAccountJoinWeibosUseJoin() {
        assert accountMapper.listAccountJoinWeibosUseJoin().size() > 0;
    }

    @Test
    public void getAccountById() {
        assert accountMapper.getAccountById(1) != null;
        assert accountMapper.getAccountById(-1) == null;
    }

    @Test
    public void lisAccountAll() {
        assert accountMapper.lisAccountAll().size() > 0;
    }

    @Test
    public void listAccountAllUseJoin() {
        assert accountMapper.listAccountAllUseJoin().size() > 0;
    }

    @Test
    public void listFlat() {
        assert accountMapper.listFlat().size() > 0;
    }

    @Test
    public void listInView() {
        assert accountMapper.listInView("jsen").size() > 0;
    }

    @Test
    public void listBetween() {
        assert accountMapper.listBetween(1, 4).size() > 0;
    }

    @Test
    public void listLimit() {
        assert accountMapper.listLimit(1, 2).size() > 0;
    }
}
