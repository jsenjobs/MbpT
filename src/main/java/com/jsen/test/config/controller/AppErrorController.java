package com.jsen.test.config.controller;

import com.alibaba.fastjson.JSONObject;
import com.jsen.test.config.shiro.exception.ExpException;
import com.jsen.test.config.shiro.exception.OtherException;
import com.jsen.test.config.shiro.exception.TokenException;
import com.jsen.test.utils.ResponseBase;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 * Created by jsen on 17/09/2016.
 * handle error request path
 */
@Controller
@CrossOrigin
class AppErrorController extends AbstractErrorController {

    private static final Logger logger = Logger.getLogger(AppErrorController.class.getName());

    private final static String ERROR_PATH = "/error";

    public AppErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }


    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public JSONObject error(Model model, HttpServletRequest request, HttpServletResponse response, Exception e) {
        logger.warning("Error:"+response.getStatus());
        int status = response.getStatus();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 1);
        System.out.println(e);

        if (e instanceof ExpException) {
            return ResponseBase.create().code(1).hcode(1).msg("token过期");
        } else if (e instanceof OtherException) {
            return ResponseBase.create().code(1).hcode(401).msg("没有访问权限");
        }  else if (e instanceof TokenException) {
            return ResponseBase.create().code(1).hcode(401).msg("非法Token");
        } else if (String.valueOf(status).startsWith("40")) {
            jsonObject.put("msg", "no api-"+status);
        } else {
            jsonObject.put("msg", "error-"+status + e.getMessage());
        }
        return jsonObject;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
