package com.group2.dingmall.utils;


import com.group2.dingmall.exceptions.ParamsException;




public class AssertUtil {
    /**
     * 断言工具 ：
     *     如果flag为true， 抛出错误信息为 msg 的自定义 ParamsException
     * @param flag
     * @param msg
     */
    public  static void isTrue(Boolean flag,String msg){
        if(flag){
            // 如果满足 flag ，抛出自定义msg异常
            throw  new ParamsException(msg);
        }
    }

}
