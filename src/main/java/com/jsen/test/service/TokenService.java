package com.jsen.test.service;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;

public interface TokenService {
    String genToken(JSONObject data, long exp) throws UnsupportedEncodingException;

    DecodedJWT validToken(String token, long exp);

    JSONObject genClaimsData(DecodedJWT decodedJWT);
}
