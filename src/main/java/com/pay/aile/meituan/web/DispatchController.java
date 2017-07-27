package com.pay.aile.meituan.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pay.aile.meituan.service.DispatchService;

/**
 *
 * @Description: 配送接口
 * @see: DispatchController 此处填写需要参考的类
 * @version 2017年7月18日 下午2:00:51
 * @author chao.wang
 */
@RestController
@RequestMapping("/dispatch")
public class DispatchController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private DispatchService dispatchService;

    /**
     *
     * @Description 众包配送-确认下单(预下单失败后调用)
     * @param orderId
     * @param tipAmount
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public String confirmZbDispatch(String orderId, String tipAmount) {
        return "";
    }

    /**
     *
     * @Description 自配送订单,订单已送达
     * @param shopId
     * @param orderId
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public String delivered(String shopId, String orderId) {
        // TODO
        return "";
    }

    /**
     *
     * @Description 订单自配送
     * @param shopId
     * @param orderId
     * @param courierName
     *            配送员姓名
     * @param courierPhone
     *            配送员电话
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public String delivering(String shopId, String orderId, String courierName, String courierPhone) {
        // TODO
        return "";
    }

    /**
     *
     * @Description 众包配送-预下单
     * @param orderId
     *            订单号
     * @param shippingFee
     *            配送费
     * @param tipAmount
     *            小费
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public String prepareZbDispatch(String orderId, String shippingFee, String tipAmount) {
        // TODO
        return "";
    }

    /**
     *
     * @Description 众包配送查询快递费
     * @param orderId
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public String queryZbShippingFee(String orderId) {
        // TODO
        return "";
    }

    /**
     *
     * @Description 众包配送-增加小费
     * @param orderId
     *            订单号
     * @param tipAmount
     *            消费金额
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public String updateZbDispatchTip(String orderId, String tipAmount) {
        // TODO
        return "";
    }
}
