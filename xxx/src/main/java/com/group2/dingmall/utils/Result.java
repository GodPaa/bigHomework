package com.group2.dingmall.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 定义响应结果统一格式
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    //业务码，比如成功、失败、权限不足等 code，可自行定义
    @ApiModelProperty("返回码")
    private int code = 200;

    //返回信息，后端在进行业务处理后返回给前端一个提示信息，可自行定义
    @ApiModelProperty("返回信息")
    private String msg = "success";

    //数据结果，泛型，可以是列表、单个对象、数字、布尔值等
    @ApiModelProperty("返回数据")
    private  T  data;

    @Override
    public String toString() {
        return "Result{" +
                "resultCode=" + code +
                ", message='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

}
