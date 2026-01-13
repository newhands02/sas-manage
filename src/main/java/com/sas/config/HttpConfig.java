package com.sas.config;

import com.sas.utils.SnowflakeIdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public SnowflakeIdGenerator snowInstance(){
        return SnowflakeIdGenerator.getInstance();
    }
}
