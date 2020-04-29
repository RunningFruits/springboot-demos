package com.code.demo.backend.exception;

import lombok.Getter;


@Getter
public class NormalException extends RuntimeException {

    private Integer status;

    public NormalException(String message) {
        super(message);
        this.status = 501;
    }

    public NormalException(Integer status, String message) {
        super(message);
        this.status = status;
    }

}
