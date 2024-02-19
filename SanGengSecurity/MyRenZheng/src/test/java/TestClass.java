import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

import java.util.List;


public class TestClass {

    @Test
    public void testTUserMapper() {
//        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9." +
//                "eyJwZXJtcyI6bnVsbCwiZXhwIjoxNzA4MzUzNjQwLCJ1c2VyaWQiOm51bGx9." +
//                "tG-IKwOoLkbP7Vx0UZKQIlWZD6M8RxysFOaRWeJsAeo";

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9." +
                "eyJwZXJtcyI6WyJzeXN0ZW06YWxsOmFsbCIsInRlc3QiXSwiZXhwIjoxNzA4MzU0MDkzLCJ1c2VyaWQiOjF9." +
                "aRCuckKLdpCbz3orp6T9nDkw2N19oPs-HLOyVsLEa_Q";

        String token1 = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwZXJtcyI6W10sImV4cCI6MTcwODM1NTYyNSwidXNlcmlkIjoyfQ.KzeF_UV9jex3hVZWYeQaazY9FiCQM0rtCCoSA2mvWE4";
        DecodedJWT decode = JWT.decode(token1);
        String userid = decode.getClaim("userid").toString();
        List<String> perms = decode.getClaim("perms").asList(String.class);

        System.out.println(userid);
        System.out.println(perms);
    }
}
