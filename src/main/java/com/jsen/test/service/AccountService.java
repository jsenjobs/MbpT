package com.jsen.test.service;

import com.alibaba.fastjson.JSONObject;
import com.jsen.test.entity.Account;
import com.baomidou.mybatisplus.service.IService;
import com.jsen.test.utils.ResponseBase;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jsen
 * @since 2018-03-22
 */
public interface AccountService extends IService<Account> {

    JSONObject login(String domain, String token);

    JSONObject create(String domain, String token, String sex);

    ResponseBase listAccountJoinWeibos();

}
