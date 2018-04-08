import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jsen.test.service.TokenService;
import com.jsen.test.service.impl.TokenServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class TokenTest {
    JSONObject data;

    long expSeconds = 14400;

    @Before
    public void initData() {
        data = new JSONObject();
        data.put("id", 1);
        data.put("username", "jsen");
        data.put("nickname", "jsen");
    }

    @Test
    public void GenToken() {
        TokenService tokenService = new TokenServiceImpl();
        String password = "368b4820fb9d190a2a42dc20d37067c76e39024d7f630100";

        try {
            String token = tokenService.genToken(data, password, expSeconds);
            DecodedJWT decodedJWT = tokenService.validToken(token, password, expSeconds);
            System.out.println(tokenService.genClaimsData(decodedJWT));
            // System.out.println(decodedJWT);
            System.out.println(token);
            System.out.println(decodedJWT.getClaim("id").asInt());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void validToken() throws UnsupportedEncodingException {
        TokenService tokenService = new TokenServiceImpl();
        String password = "368b4820fb9d190a2a42dc20d37067c76e39024d7f630100";


        // com.auth0.jwt.exceptions.JWTDecodeException: The string '{"typ����|b�|b�[Ȏ��̍M��' doesn't have a valid JSON format.
        // com.auth0.jwt.exceptions.TokenExpiredException: The Token has expired on Mon Mar 26 09:41:07 CST 2018.
        // System.out.println(tokenService.validToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJpZFwiOlwiMTJcIn0iLCJuaWNrbmFtZSI6ImphY2siLCJpc3MiOiJqc2VuIiwiaWQiOiIxMiIsImV4cCI6MTUyMzE4Mjg4NywiaWF0IjoxNTIzMTcyODg3LCJ1c2VybmFtZSI6ImpzZW4ifQ.-mDG8RKYoexbDtHanGQqtCM7uUKXEFHmuBgKsODgJsw", password, expSeconds));
    }
}
