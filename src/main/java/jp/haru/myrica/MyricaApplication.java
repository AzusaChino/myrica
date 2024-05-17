package jp.haru.myrica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * GRPC server
 *
 * @author haru
 * @since 2021-09-15 17:08
 */
@SpringBootApplication
public class MyricaApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MyricaApplication.class, args);
    }
}
