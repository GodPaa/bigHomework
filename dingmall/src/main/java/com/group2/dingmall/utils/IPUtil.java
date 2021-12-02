package com.group2.dingmall.utils;
import com.group2.dingmall.exceptions.ParamsException;
import lombok.SneakyThrows;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author lv
 * @Date 2021/12/2 11:25
 * @Description
 **/

public class IPUtil {
    public static String getCurrentIP() {
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new ParamsException("获取服务器IP失败");
        }
        return addr.getHostAddress();
    }

}
