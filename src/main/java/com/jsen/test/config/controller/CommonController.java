package com.jsen.test.config.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.jsen.test.config.shiro.exception.ExpException;
import com.jsen.test.config.shiro.exception.OtherException;
import com.jsen.test.utils.ResponseBase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CommonController {
    @RequestMapping("/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public JSONObject _401() {
        return ResponseBase.create().code(1).msg("401");
    }
}
