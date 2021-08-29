package com.youngjae.feign.client.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngjae.feign.client.demo.domain.CommonResponse;
import com.youngjae.feign.client.demo.domain.User;
import com.youngjae.feign.client.demo.feign.FeignLocalClient;

@Service
public class LocalFeignService {

    @Autowired
    private FeignLocalClient feignLocalClient;

    public String callNoParamAPI() {
        return feignLocalClient.noParams();
    }

    public String callYesParamAPI(String name, Integer age) {
        return feignLocalClient.yesParams(name, age);
    }

    public String callYesPathVariableAPI(String userName) {
        return feignLocalClient.yesPathVariable(userName);
    }

    public User callReturnObjectAPI(String name, Integer age) {
        return feignLocalClient.returnObject(name, age);
    }

    public String callReturnStringAPI(String name, Integer age) {
        return feignLocalClient.returnString(name, age);
    }

    public String callYesBodyAPI(User user) {
        return feignLocalClient.yesBody(user);
    }

    public String callYesDynamicHeaderAPI(String testHeader) {
        return feignLocalClient.yesDynamicHeader(testHeader);
    }

}
