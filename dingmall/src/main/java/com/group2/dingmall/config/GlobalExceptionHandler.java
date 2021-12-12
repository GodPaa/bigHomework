package com.group2.dingmall.config;



import com.group2.dingmall.exceptions.ParamsException;
import com.group2.dingmall.utils.Result;

import com.group2.dingmall.utils.ResultGenerator;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
              // @Valid这种校验性需要另外捕获下MethodArgumentNotValidException以及Bind这个异常类 ??? 不知道为什么不行
              else if (e instanceof MethodArgumentNotValidException){
                  System.out.println("异常异常");
                  String msg = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
                  return ResultGenerator.genFailResult(msg);
              }
              // @valid 检测出的参数格式异常
              else if (e instanceof BindException){
                  System.out.println("异常异常");
                  String msg = ((BindException) e).getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
                  return ResultGenerator.genFailResult(msg);
              }
              // 其他异常
              else {
                  return ResultGenerator.genFailResult(e.getMessage());
              }

    }




}
