package com.jsen.test.utils.service;

import com.jsen.test.Boot;
import com.jsen.test.service.WeiboService;
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
public class WeiboServiceTest {

    @Autowired
    WeiboService weiboService;

    @Test
    public void getWeibos() {
        assert weiboService.getWeibos("jsen").getJSONArray("data").size() > 0;
        assert weiboService.getWeibos("jack").getJSONArray("data").size() == 0;
    }
}
