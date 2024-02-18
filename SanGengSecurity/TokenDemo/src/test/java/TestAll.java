import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

public class TestAll {
    @Test
    public void testAuth(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9." +
                "eyJwZXJtaXMiOlsic3lzdGVtOmRlcHQ6YWxsIl0sImV4cCI6MTcwODE4NDc4NCwidXNlcmlkIjoxfQ." +
                "xKfE6rnLMt72wvUPXv3_zn5E1eyV0Z-CJfKHP2i83pI";
        DecodedJWT decode = JWT.decode(token);
//        Claim permis = decode.getClaim("permis");
//
//        System.out.println(decode.getClaim("permis").toString());

        System.out.println(decode.getClaim("userid"));
    }
}
