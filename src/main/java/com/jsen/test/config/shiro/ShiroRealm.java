package com.jsen.test.config.shiro;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.jsen.test.config.shiro.exception.ExpException;
import com.jsen.test.config.shiro.exception.OtherException;
import com.jsen.test.service.TokenService;
import com.jsen.test.service.impl.AccountServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiroRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Autowired
    TokenService tokenService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return new SimpleAuthorizationInfo();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
        String token = (String) authenticationToken.getCredentials();
        tokenService.validToken(token, AccountServiceImpl.shortExp);

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
