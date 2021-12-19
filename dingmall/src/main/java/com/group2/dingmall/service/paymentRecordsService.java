package com.group2.dingmall.service;

import com.group2.dingmall.po.AliReturnPayBean;
import org.springframework.stereotype.Service;

/**
 * @Author lv
 * @Date 2021/12/18 13:46
 * @Description
 **/

@Service
public class paymentRecordsService {

    // 拿到支付成功结果后，修改其它表
    public void aliPaySuccess(AliReturnPayBean returnPay) {
    }
}
