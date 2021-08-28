package com.youngjae.feign.client.demo.domain;

import lombok.Getter;

@Getter
public class CommonResponse<T> {
    private String returnCode;
    private T info;
}
