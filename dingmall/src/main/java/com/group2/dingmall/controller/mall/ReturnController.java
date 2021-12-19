package com.group2.dingmall.controller.mall;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group2.dingmall.po.AliReturnPayBean;
import com.group2.dingmall.service.paymentRecordsService;
import com.group2.dingmall.utils.payUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/returnPay")
public class ReturnController {

    private static Logger logger = LoggerFactory.getLogger(ReturnController.class);
    @Resource
    private paymentRecordsService paymentRecordsService;

    /**
     * 支付宝回调的接口
     *
     * @param
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/aliReturnPay", method = RequestMethod.POST)
    public void returnPay(HttpServletResponse response, AliReturnPayBean returnPay, HttpServletRequest req)
            throws IOException {
        response.setContentType("type=text/html;charset=UTF-8");
        logger.info("****************************************支付宝的的回调函数被调用******************************");
        if (!payUtil.checkSign(req)) {
            logger.info("****************************************验签失败*******************************************");
            response.getWriter().write("failture");
            return;
        }
        if (returnPay == null) {
            logger.info("支付宝的returnPay返回为空");
            response.getWriter().write("success");
            return;
        }
        logger.info("支付宝的returnPay" + returnPay.toString());
        if (returnPay.getTrade_status().equals("TRADE_SUCCESS")) {
            logger.info("支付宝的支付状态为TRADE_SUCCESS");
            paymentRecordsService.aliPaySuccess(returnPay);
        }
        response.getWriter().write("success");
    }
}