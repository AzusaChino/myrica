package cn.az.code.img;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring native image application
 *
 * @author haru
 */
@SpringBootApplication
public class SpringImgApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringImgApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringImgApplication.class);
        LOGGER.info("you can make it!!!");
    }
}
