package com.youngjae.feign.client.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.youngjae.feign.client.demo.feign")
public class FeignConfig {

}
