package com.youngjae.feign.client.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.youngjae.feign.client.demo.domain.User;
import com.youngjae.feign.client.demo.service.LocalFeignService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ExternalController {

    @GetMapping("/no-params")
    public String noParams() {
        return "noParams!";
    }

    @GetMapping("/yes-params")
    public String yesParams(@RequestParam String name,
                            @RequestParam Integer age) {
        return name + " : " + age;
    }

    @GetMapping("/return-object")
    public User returnObject(@RequestParam String name,
                             @RequestParam Integer age) {
        return new User(name, age);
    }

    @GetMapping("/{user-name}/yes-path-variable")
    public String yesPathVariable(@PathVariable("user-name") String userName) {
        return userName;
    }

    @PostMapping("/yes-body")
    public String yesBody(@RequestBody User user) {
        return user.getName();
    }

    @GetMapping("/yes-header")
    public String yesHeader(HttpServletRequest request) {
        String contentType = request.getHeader("content-type");
        String headerTest = request.getHeader("test-header");

        return "contentType : " + contentType + ", headerTest : " + headerTest;
    }

    @GetMapping("/test-interceptor")
    public String testInterceptor(@RequestHeader("interceptor") String header) {
        return header;
    }

    @GetMapping("/return-500")
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String return500() {
        return null;
    }

}
