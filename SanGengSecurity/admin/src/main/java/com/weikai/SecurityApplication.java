package com.weikai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@MapperScan({"com.weikai.mapper"})
@EnableTransactionManagement
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityApplication {
    public static void main(String[] args) {
        System.out.println(
                        "                                                                               _ooOoo_\n" +
                        "                                                                              o8888888o\n" +
                        "                                                                              88\" . \"88\n" +
                        "                                                                              (| -_- |)\n" +
                        "                                                                              O\\  =  /O\n" +
                        "                                                                           ____/`---'\\____\n" +
                        "                                                                         .'  \\\\|     |//  `.\n" +
                        "                                                                        /  \\\\|||  :  |||//  \\\n" +
                        "                                                                       /  _||||| -:- |||||-  \\\n" +
                        "                                                                       |   | \\\\\\  -  /// |   |\n" +
                        "                                                                       | \\_|  ''\\---/''  |   |\n" +
                        "                                                                       \\  .-\\__  `-`  ___/-. /\n" +
                        "                                                                     ___`. .'  /--.--\\  `. . __\n" +
                        "                                                                  .\"\" '<  `.___\\_<|>_/___.'  >'\"\".\n" +
                        "                                                                 | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |\n" +
                        "                                                                 \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /\n" +
                        "                                                            ======`-.____`-.___\\_____/___.-`____.-'======\n" +
                        "                                                                               `=---='\n" +
                        "                                                            ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n" +
                        "                                                                   // 佛祖保佑 永不宕机 永无BUG //\n");
        ConfigurableApplicationContext run = SpringApplication.run(SecurityApplication.class, args);
        System.out.println("SanGenSecurity 启动成功！");

    }
}
