package com.youngjae.feign.client.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.youngjae.feign.client.demo.domain.User;

import feign.Headers;
import feign.RequestLine;

@FeignClient(name ="localhost", url = "http://localhost:8080")
public interface FeignLocalClient {

    @GetMapping("/no-params")
    String noParams();

    @GetMapping("/yes-params")
    String yesParams(@RequestParam String name, @RequestParam Integer age);

    @GetMapping("/return-object")
    String returnObject(@RequestParam String name, @RequestParam Integer age);

    @PostMapping("/yes-body")
    String yesBody(@RequestBody User user);

    @Headers({
            "accept:application/json",
            "content-type:application/json; charset=utf-8",
    })
    @RequestLine("GET /yes-header")
    String yesHeader(@RequestHeader("test-header") String header);
}
