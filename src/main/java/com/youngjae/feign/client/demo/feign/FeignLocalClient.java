package com.youngjae.feign.client.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.youngjae.feign.client.demo.config.FeignLocalClientConfiguration;
import com.youngjae.feign.client.demo.domain.User;

@FeignClient(name ="localhost", url = "http://localhost:8080", configuration = FeignLocalClientConfiguration.class)
public interface FeignLocalClient {

    @GetMapping("/no-params")
    String noParams();

    @GetMapping("/yes-params")
    String yesParams(@RequestParam String name, @RequestParam Integer age);

    @GetMapping("/{user-name}/yes-path-variable")
    String yesPathVariable(@PathVariable("user-name") String userName);     // 반드시 @PathVariable의 value 값을 지정해야 한다.


    @GetMapping("/return-object")
    String returnString(@RequestParam String name, @RequestParam Integer age); // Object로 리턴하는 API를 String으로 받을 수 있다.

    @GetMapping("/return-object")
    User returnObject(@RequestParam String name, @RequestParam Integer age);

    @PostMapping("/yes-body")
    String yesBody(@RequestBody User user);

    @GetMapping(value = "/yes-header", headers = {"content-type=application/json; charset=utf-8", "accept=application/json"})
    String yesDynamicHeader(@RequestHeader("test-header") String testHeader);

    @GetMapping(value = "/test-interceptor")
    String testInterceptor();

    @GetMapping("/return-500")
    String return500();
}
