package com.jsen.test.controller.sql;


import com.jsen.test.service.AccountService;
import com.jsen.test.service.WeiboService;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// sql test
@RestController
@CrossOrigin
@RequestMapping("/sql")
public class SqlTest {

    @Autowired
    WeiboService weiboService;
    @Autowired
    AccountService accountService;

    @GetMapping("/union/weibojoinaccount")
    public ResponseBase union001() {
        return weiboService.getWeibos().msg("一对一，使用join");
    }

    @GetMapping("/union/accountjoinweibos")
    public ResponseBase union002() {
        return accountService.listAccountJoinWeibos().msg("一对多，不是join，直接select");
    }

}
