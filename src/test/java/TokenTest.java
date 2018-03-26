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

    long expSeconds = 1;

    @Before
    public void initData() {
        data = new JSONObject();
        data.put("id", UUID.randomUUID().toString());
        data.put("username", "jsen");
        data.put("nickname", "jack");
    }

    @Test
    public void GenToken() {
        TokenService tokenService = new TokenServiceImpl();

        try {
            String token = tokenService.genToken(data, expSeconds);
            DecodedJWT decodedJWT = tokenService.validToken(token, expSeconds);
            System.out.println(tokenService.genClaimsData(decodedJWT));
            System.out.println(decodedJWT);
            System.out.println(token);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void validToken() throws UnsupportedEncodingException {
        TokenService tokenService = new TokenServiceImpl();
        // com.auth0.jwt.exceptions.JWTDecodeException: The string '{"typ����|b�|b�[Ȏ��̍M��' doesn't have a valid JSON format.
        // com.auth0.jwt.exceptions.TokenExpiredException: The Token has expired on Mon Mar 26 09:41:07 CST 2018.
        System.out.println(tokenService.validToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJpZFwiOlwiYjY4NmRjNTktYWE5NC00MWRiLTg4OWQtMjE4YmM5MTVmZTk1XCJ9Iiwibmlja25hbWUiOiJqYWNrIiwiaXNzIjoianNlbiIsImlkIjoiYjY4NmRjNTktYWE5NC00MWRiLTg4OWQtMjE4YmM5MTVmZTk1IiwiZXhwIjoxNTIyMDI4NDY3LCJpYXQiOjE1MjIwMjg0NjYsImp0aSI6Imp3dCIsInVzZXJuYW1lIjoianNlbiJ9.ioCoZdB3EJy1HCQ6_VwvZpf0kgiBM5vJjcWINhHGqn8", expSeconds));
    }
}
