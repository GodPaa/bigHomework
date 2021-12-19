package com.group2.dingmall;


import com.group2.dingmall.utils.payUtil;

import javax.annotation.Resource;

public class testPayment {

    public static void main(String[] args) {

        payUtil payUtil = null;

        String str = payUtil.alipay("2313221311", "0.01", "hehe", "haha");
        System.out.println(str);
    }

}