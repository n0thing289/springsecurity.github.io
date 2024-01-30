import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TestPasswordEncoder {
    @Test
    public void testGenerate(){
        String password = new BCryptPasswordEncoder().encode("123456");
        System.out.println(password);//$2a$10$X587eQNSRu1BFM6yKCh8le2sLMPkqpOElVIYNhDi/KELCzZgT0G3a
    }
}
