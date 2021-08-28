package com.youngjae.feign.client.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.youngjae.feign.client.demo.domain.User;
import com.youngjae.feign.client.demo.service.LocalFeignService;

@RestController
public class FeignClientController {

    @Autowired
    private LocalFeignService feignService;

    @GetMapping("/call-no-params")
    public String callExternalApi() {
        return feignService.callNoParamAPI();
    }

    @GetMapping("/call-yes-params")
    public String callYesParamsApi(@RequestParam String name,
                                   @RequestParam Integer age) {
        return feignService.callYesParamAPI(name, age);
    }

    // User를 String으로 받으면 Json String으로 받아진다.
    @GetMapping("/call-return-object")
    public String callReturnObjectApi(@RequestParam String name,
                                   @RequestParam Integer age) {
        return feignService.callReturnObjectAPI(name, age);
    }

    @PostMapping("/call-yes-body")
    public String yesBody(@RequestBody User user) {
        return feignService.callYesBodyAPI(user);
    }

    @GetMapping("/call-yes-header")
    public String yesHeader(@RequestParam String testHeader) {
        return feignService.callYesHeaderAPI(testHeader);
    }
}
