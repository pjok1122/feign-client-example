package com.youngjae.feign.client.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youngjae.feign.client.demo.feign.FeignLocalClient;
import com.youngjae.feign.client.demo.service.LocalFeignService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FeignTestController {

    private final LocalFeignService service;
    private final FeignLocalClient feignLocalClient;

    @GetMapping("/test/test-interceptor")
    public String testInterceptor() {
        return service.testInterceptor();
    }

    @GetMapping("/test/return-500")
    public String testReTry() {
        return feignLocalClient.return500();
    }
}
