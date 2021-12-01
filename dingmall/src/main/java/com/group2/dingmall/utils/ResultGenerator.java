package com.group2.dingmall.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 响应结果生成工具
 *
 * @author 13
 * @qq交流群 796794009
 * @email 2449207463@qq.com
 * @link https://github.com/newbee-ltd
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_Msg = "SUCCESS";
    private static final String DEFAULT_FAIL_Msg = "FAIL";
    private static final int RESULT_CODE_SUCCESS = 200;
    private static final int RESULT_CODE_SERVER_ERROR = 500;

    public static Result genSuccessResult() {
        Result result = new Result();
        result.setCode(RESULT_CODE_SUCCESS);
        result.setMsg(DEFAULT_SUCCESS_Msg);
        return result;
    }

    public static Result genSuccessResult(String Msg) {
        Result result = new Result();
        result.setCode(RESULT_CODE_SUCCESS);
        result.setMsg(Msg);
        return result;
    }

    public static Result genSuccessResult(Object data) {
        Result result = new Result();
        result.setCode(RESULT_CODE_SUCCESS);
        result.setMsg(DEFAULT_SUCCESS_Msg);
        result.setData(data);
        return result;
    }

    public static Result genFailResult(String Msg) {
        Result result = new Result();
        result.setCode(RESULT_CODE_SERVER_ERROR);
        if (StringUtils.isBlank(Msg)) {
            result.setMsg(DEFAULT_FAIL_Msg);
        } else {
            result.setMsg(Msg);
        }
        return result;
    }

    //  other error (可以设置特殊 code 和 msg)
    public static Result genErrorResult(int code, String Msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(Msg);
        return result;
    }
}
