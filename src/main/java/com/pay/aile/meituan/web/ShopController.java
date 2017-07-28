package com.pay.aile.meituan.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.pay.aile.meituan.service.ShopService;
import com.pay.aile.meituan.util.JsonFormatUtil;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private ShopService shopService;

    /**
     *
     * @Description 设置店铺为自动接单
     * @param shopId
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping("/autoConfirm")
    public JSONObject autoConfirm(@RequestParam String shopId) {
        try {
            shopService.autoConfirmOrder(shopId);
            return JsonFormatUtil.getSuccessJson();
        } catch (Exception e) {
            logger.error("shop autoConfirm error!shopId={}", shopId, e);
            return JsonFormatUtil.getFailureJson();
        }
    }

    /**
     *
     * @Description 设置门店休息
     * @param shopId
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping("/close")
    public JSONObject close(@RequestParam String shopId) {
        try {
            shopService.close(shopId);
            return JsonFormatUtil.getSuccessJson();
        } catch (Exception e) {
            logger.error("shop close error!shopId={}", shopId, e);
            return JsonFormatUtil.getFailureJson();
        }
    }

    /**
     *
     * @Description 设置门店营业
     * @param shopId
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping("/open")
    public JSONObject open(@RequestParam String shopId) {
        try {
            shopService.open(shopId);
            return JsonFormatUtil.getSuccessJson();
        } catch (Exception e) {
            logger.error("shop open error!shopId={}", shopId, e);
            return JsonFormatUtil.getFailureJson();
        }
    }

    /**
     *
     * @Description 设置店铺为不自动接单
     * @param shopId
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping("/unAutoConfirm")
    public JSONObject unAutoConfirm(@RequestParam String shopId) {
        try {
            shopService.unAutoConfirmOrder(shopId);
            return JsonFormatUtil.getSuccessJson();
        } catch (Exception e) {
            logger.error("shop unAutoConfirm error!shopId={}", shopId, e);
            return JsonFormatUtil.getFailureJson();
        }
    }
}
