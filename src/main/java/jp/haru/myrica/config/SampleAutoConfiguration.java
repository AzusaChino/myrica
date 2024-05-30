package jp.haru.myrica.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * Auto Configuration
 * 
 * - spring.factories
 * - spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
 */
@AutoConfiguration
public class SampleAutoConfiguration {

    @Bean
    @Primary
    String theOnlyBean() {
        return "the only bean";
    }

}
