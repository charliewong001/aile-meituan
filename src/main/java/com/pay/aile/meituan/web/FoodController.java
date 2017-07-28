package com.pay.aile.meituan.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.pay.aile.meituan.service.FoodService;
import com.pay.aile.meituan.util.JsonFormatUtil;

/**
 *
 * @Description: 菜品接口
 * @see: FoodController 此处填写需要参考的类
 * @version 2017年7月21日 下午5:04:30
 * @author chao.wang
 */
@RestController
@RequestMapping("/food")
public class FoodController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private FoodService foodService;

    /**
     *
     * @Description 估清菜品
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping("/toEstimate")
    public JSONObject toEstimate(@RequestParam("shopId") String shopId,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("foodId") String foodId) {
        try {
            foodService.toEstimate(shopId, categoryId, foodId);
            return JsonFormatUtil.getSuccessJson();
        } catch (Exception e) {
            logger.error("toEstimate error!,shopId={},foodId={}", shopId,
                    foodId, e);
            return JsonFormatUtil.getFailureJson();
        }
    }

    /**
     *
     * @Description 置满菜品
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    @RequestMapping("/toFull")
    public JSONObject toFull(@RequestParam("shopId") String shopId,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("foodId") String foodId) {
        try {
            foodService.toFull(shopId, categoryId, foodId);
            return JsonFormatUtil.getSuccessJson();
        } catch (Exception e) {
            logger.error("toFull error!,shopId={},foodId={}", shopId, foodId,
                    e);
            return JsonFormatUtil.getFailureJson();
        }
    }
}
