import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestPasswordEncoder {

    @Test
    public void testPassword(){
        String encode = new BCryptPasswordEncoder().encode("111");
        System.out.println(encode);
    }
}
