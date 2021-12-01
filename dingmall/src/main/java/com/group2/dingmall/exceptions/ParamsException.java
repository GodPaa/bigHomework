package com.group2.dingmall.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义参数异常
 */
@Getter
@Setter
public class ParamsException extends RuntimeException {

    // 默认msg
    private String msg="参数异常!";


    public ParamsException() {
        super("参数异常!");
    }

    public ParamsException(String msg) {
        super(msg);
        this.msg = msg;
    }

}
