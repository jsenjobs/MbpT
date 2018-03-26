package com.jsen.test.config.controller;

import com.alibaba.fastjson.JSONObject;
import com.jsen.test.config.shiro.exception.TokenException;
import com.jsen.test.utils.ResponseBase;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TokenException.class)
    public JSONObject _token() {
        return ResponseBase.create().code(1).msg("非法token");
    }
}
