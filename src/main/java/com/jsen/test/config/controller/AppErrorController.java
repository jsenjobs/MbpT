package com.jsen.test.config.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.jsen.test.config.shiro.exception.ExpException;
import com.jsen.test.config.shiro.exception.OtherException;
import com.jsen.test.config.shiro.exception.TokenException;
import com.jsen.test.utils.ResponseBase;
import org.springframework.boot.autoconfigure.web.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by jsen on 17/09/2016.
 * handle error request path
 */
@RestController
@CrossOrigin
class AppErrorController extends BasicErrorController {

    private static final Logger logger = Logger.getLogger(AppErrorController.class.getName());

    public AppErrorController(ServerProperties serverProperties) {
        super(new JDefaultErrorAttributes(), serverProperties.getError());
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = this.getErrorAttributes(request, this.isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = this.getStatus(request);
        return new ResponseEntity(body, status);
    }
}
