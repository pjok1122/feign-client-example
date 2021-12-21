package com.youngjae.feign.client.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import com.youngjae.feign.client.demo.domain.User;

/**
 * LocalFeignServiceTest
 *
 * @author youngjae park (youngjae.p@linecorp.com)
 * @since 2021.08.29
 */
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@EnableFeignClients
@ComponentScan(basePackages = "com/youngjae/feign/client/demo/config")
class LocalFeignServiceTest {
    @Autowired
    private LocalFeignService feignService;

    @Test
    public void callNoParamAPI() {
        //when
        String result = feignService.callNoParamAPI();

        //then
        Assertions.assertThat(result).isEqualTo("noParams!");
    }

    @Test
    public void callYesParamAPI() {
        //given
        String name = "youngjae";
        int age = 29;

        //when
        String result = feignService.callYesParamAPI(name, age);

        //then
        Assertions.assertThat(result).isEqualTo("youngjae : 29");
    }

    @Test
    public void callReturnObjectAPI() {
        //given
        String name = "youngjae";
        int age = 29;

        //when
        User user = feignService.callReturnObjectAPI(name, age);

        //then
        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getName()).isEqualTo(name);
        Assertions.assertThat(user.getAge()).isEqualTo(age);
    }

    @Test
    public void callReturnStringAPI() {
        //given
        String name = "youngjae";
        int age = 29;

        //when
        String result = feignService.callReturnStringAPI(name, age);

        //then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isEqualTo("{\"name\":\"youngjae\",\"age\":29}");
    }

    @Test
    public void callYesBodyAPI() {
        //given
        User user = new User("youngjae", 29);

        //when
        String result = feignService.callYesBodyAPI(user);

        //then
        Assertions.assertThat(result).isEqualTo("youngjae");
    }

    @Test
    public void callYesPathVariableAPI() {
        //given
        String userName = "youngjae-park";

        //when
        String result = feignService.callYesPathVariableAPI(userName);

        //then
        Assertions.assertThat(result).isEqualTo(userName);
    }

    @Test
    public void callYesHeaderAPI() {
        //when
        String result = feignService.callYesDynamicHeaderAPI("test-Header");

        //then
        Assertions.assertThat(result).isEqualTo("contentType : application/json; charset=utf-8, headerTest : test-Header");
    }

    @Test
    public void testInterceptor() {
        //when
        String header = feignService.testInterceptor();

        //then
        Assertions.assertThat(header).isEqualTo("interceptor1");
    }

}