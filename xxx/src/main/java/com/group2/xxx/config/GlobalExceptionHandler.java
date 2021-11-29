package com.group2.xxx.config;



import com.group2.xxx.exceptions.ParamsException;
import com.group2.xxx.utils.Result;

import com.group2.xxx.utils.ResultGenerator;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 定义一个全局处理异常类
 */
//@RestController+@ControllerAdvice=@RestControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

    //处理异常的方法  并确定接收的是哪种类型的异常
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e)
    {
              // 捕获自定义参数的异常
              if(e instanceof ParamsException)
              {
                  return ResultGenerator.genFailResult(((ParamsException) e).getMsg());
              }
              // 其他异常
              else {
                  return ResultGenerator.genFailResult(e.getMessage());
              }

    }


}
