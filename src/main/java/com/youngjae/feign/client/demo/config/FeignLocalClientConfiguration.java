package com.youngjae.feign.client.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import feign.Logger;
import feign.Request.HttpMethod;
import feign.RequestInterceptor;
import feign.Response;
import feign.RetryableException;
import feign.Retryer;
import feign.Retryer.Default;
import feign.Util;
import feign.codec.ErrorDecoder;

// @Configuration을 붙이지 않는다.
public class FeignLocalClientConfiguration {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header("interceptor", "interceptor1");
    }

    // logging.level.com.youngjae.feign.client.demo.feign.FeignLocalClient=DEBUG 로 등록을 해야 동작함.
    // Feign logging은 DEBUG 레벨에서만 동작을 한다.
    @Bean
    public Logger.Level feignLoggerLevel() {
        // NONE : default
        // BASIC : request method, url, response status, execution time 정도만 기록
        // HEADERS : BASIC + request/response header
        // FULL : HEADER + request/response body
        return Logger.Level.FULL;
    }

    /* ErrorDecoder에서 RetryableException이 발생했을 때만 재시도를 시도한다.
    * 각 요청은 period * 1.5를 곱해나가는데, 만약 maxPeriod보다 커지게 될 경우 maxPeriod 값으로 fix.
    * */
    @Bean
    public Retryer retryer() {
        //period : 각 시도 간의 시간 차이
        //maxPeriod : 최대 대기 시간
        //maxAttempts : 최대 시도 횟수
        return new Default(500L, 1000L, 5);
    }


    /*
     ErrorDecoder가 호출되는 경우 : 상태 코드가 2xx가 아닌 경우
     RetryableException 예외를 반환하는 경우(default): 503 에러 코드
     RetryableException이 발생하는 경우에는 등록된 Retryer에 따라 재시도가 동작한다.
     */
    @Bean
    public ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            if (HttpStatus.valueOf(response.status()).is5xxServerError()) {
                return new RetryableException(response.status(), "message", response.request().httpMethod(), null, response.request());
            }
            return new IllegalArgumentException(
                    String.format("[MAP_API_RETRY_FAIL] : method: %s, status : %s, headers: %s", methodKey,
                                  response.status(), response.headers()));
        };
    }
}
