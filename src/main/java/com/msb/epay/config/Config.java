package com.msb.epay.config;

import org.springframework.context.annotation.*;
import org.springframework.web.client.*;

@Configuration
public class Config {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
