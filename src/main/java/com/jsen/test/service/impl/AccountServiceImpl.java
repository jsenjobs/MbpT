package com.jsen.test.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jsen.test.config.dbs.help.DS;
import com.jsen.test.config.dbs.help.DbTypes;
import com.jsen.test.entity.Account;
import com.jsen.test.mapper.AccountMapper;
import com.jsen.test.service.AccountService;
import com.jsen.test.service.TokenService;
import com.jsen.test.utils.MD5Util;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jsen
 * @since 2018-03-22
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Autowired
    TokenService tokenService;
    // 4 hour
    public static long shortExp = 60 * 60 * 4;
    public static long LongExp = 60 * 60 * 24 * 7;

    @Override
    public JSONObject login(String domain, String token) {
        Account account = baseMapper.selectOne(new Account().setName(domain));
        if (account != null && MD5Util.verify(token, account.getPassword())) {
            // create token
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", account.getId());
            jsonObject.put("username", account.getName());
            jsonObject.put("nickname", account.getName());
            try {
                String tk = tokenService.genToken(jsonObject, shortExp);
                String rTk = tokenService.genToken(jsonObject, LongExp);
                return ResponseBase.create().code(0).add("token", tk).add("rToken", rTk);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return ResponseBase.create().code(1).msg("获取token失败");
            }
        }
        return ResponseBase.create().code(1).msg("验证错误");
    }

    @Override
    public JSONObject create(String domain, String token, String sex) {
        Account account = baseMapper.selectOne(new Account().setName(domain));
        if (account == null) {
            account = new Account();
            if (account.setName(domain).setPassword(MD5Util.generate(token)).setLastlogin(new Date()).setSex(sex).insert()) {
                return ResponseBase.create().code(0);
            } else {
                return ResponseBase.create().code(1).msg("创建用户失败");
            }
        } else {
            return ResponseBase.create().code(1).msg("用户存在");
        }
    }

    @Override
    public ResponseBase listAccountJoinWeibos() {
        return ResponseBase.create().code(0).add("data", baseMapper.listAccountJoinWeibos());
    }

    @Override
    public ResponseBase listAccountJoinWeibosUseJoin() {
        return ResponseBase.create().code(0).add("data", baseMapper.listAccountJoinWeibosUseJoin());
    }

    @Override
    public ResponseBase listAccountAll() {
        return ResponseBase.create().code(0).add("data", baseMapper.lisAccountAll());
    }

    @Override
    public ResponseBase listAccountAllUseJoin() {
        return ResponseBase.create().code(0).add("data", baseMapper.listAccountAllUseJoin());
    }

    @Override
    public ResponseBase listFlat() {
        long t1 = System.currentTimeMillis();
        List l = baseMapper.listFlat();
        System.out.println(System.currentTimeMillis() - t1);
        return ResponseBase.create().code(0).add("data", l);
    }

    @Override
    public ResponseBase listInfoInView(String name) {
        return ResponseBase.create().code(0).add("data", baseMapper.listInView(name));
    }

    @Override
    public ResponseBase listBetween(int id1, int id2) {
        return ResponseBase.create().code(0).add("data", baseMapper.listBetween(id1, id2));
    }

    @Override
    public ResponseBase listLimit(int offset, int limit) {
        return ResponseBase.create().code(0).add("data", baseMapper.listLimit(offset, limit));
    }

    @Override
    public ResponseBase deleteDistinct() {
        return ResponseBase.create().code(0).add("effect", baseMapper.deleteDistinct());
    }

    @Override
    public ResponseBase randomList() {
        return ResponseBase.create().code(0).add("data", baseMapper.listRandom());
    }

    @Override
    public ResponseBase updateNameById(int id, String name) {
        int eff = baseMapper.updateById(new Account().setId(id).setName(name));
        return ResponseBase.create().code(0).add("effect", eff);
    }

    @Override
    public ResponseBase updateId(int id, int newId) {
        int eff = baseMapper.updateId(id, newId);
        return ResponseBase.create().code(0).add("effect", eff);
    }

    @Override
    public ResponseBase deleteById(int id) {
        int eff = baseMapper.deleteById(id);
        return ResponseBase.create().code(0).add("effect", eff);
    }

    @Override
    @DS(DbTypes.DB2)
    public ResponseBase listDb2() {
        return ResponseBase.create().code(0).msg("db2").add("data", baseMapper.listAll());
    }

    @Override
    @DS(DbTypes.DB1)
    public ResponseBase listDb1() {
        return ResponseBase.create().code(0).msg("db2").add("data", baseMapper.listAll());
    }


}
