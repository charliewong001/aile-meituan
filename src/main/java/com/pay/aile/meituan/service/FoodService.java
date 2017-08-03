package com.pay.aile.meituan.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pay.aile.meituan.bean.Constants;
import com.pay.aile.meituan.bean.jpa.Food;
import com.pay.aile.meituan.bean.jpa.Platform;
import com.pay.aile.meituan.bean.jpa.PlatformCodeEnum;
import com.pay.aile.meituan.bean.jpa.Shop;
import com.pay.aile.meituan.bean.jpa.StatusEnum;
import com.pay.aile.meituan.bean.platform.DishBaseBean;
import com.pay.aile.meituan.bean.platform.DishBean;
import com.pay.aile.meituan.bean.platform.DishMapping;
import com.pay.aile.meituan.bean.platform.DishMapping.DishSkuMapping;
import com.pay.aile.meituan.bean.platform.DishSkuBean;
import com.pay.aile.meituan.bean.platform.DishStock;
import com.pay.aile.meituan.client.JpaClient;
import com.pay.aile.meituan.sdk.MeituanConfig;
import com.pay.aile.meituan.util.JsonFormatUtil;
import com.sankuai.sjst.platform.developer.domain.RequestSysParams;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutDishBaseQueryByEPoiIdRequest;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutDishMapRequest;
import com.sankuai.sjst.platform.developer.request.CipCaterTakeoutDishStockUpdateRequest;

/**
 *
 * @Description: 菜品服务
 * @see: FoodService 此处填写需要参考的类
 * @version 2017年7月21日 下午5:09:07
 * @author chao.wang
 */
@Service
public class FoodService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private JpaClient jpaClient;

    /**
     *
     * @Description 批量保存菜品信息
     * @param food
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public void batchSaveFoodInfo(String shopId, List<Food> foods) {
        logger.info("batchSaveFoodInfo foods={}", foods);
        JSONObject json = new JSONObject();
        json.put("shopId", shopId);
        json.put("foodList", foods);
        json.put("platformCode", PlatformCodeEnum.mt.getCode());
        JSONObject result = null;
        try {
            result = jpaClient.bathSaveOrUpdate(json.toJSONString());
        } catch (Exception e) {
            logger.error("调用jpa接口批量新增菜品失败,shopId={},foods={}", shopId, foods, e);
            throw new RuntimeException("批量新增菜品失败");
        }
        if (result == null || !"0".equals(result.getString("code"))) {
            String msg = result == null ? "" : result.getString("msg");
            logger.error("调用jpa接口批量新增菜品失败!msg={}", msg);
            throw new RuntimeException("批量新增菜品失败,".concat(msg));
        }
        logger.info("batchSaveFoodInfo 批量保存菜品信息成功");
    }

    /**
     *
     * @Description 菜品映射
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public void mapFoodToDish(String shopId) {
        // 去美团查询店铺所有菜品
        List<DishBean> dishes = queryAllFoodsFromPlatform(shopId);
        // 保存查询到的菜品到本地并设置映射数据
        List<Food> foods = new ArrayList<Food>();
        List<DishMapping> dishMappings = new ArrayList<DishMapping>();
        dishes.forEach((dish) -> {
            List<DishSkuBean> skus = dish.getWaiMaiDishSkuBases();
            if (skus == null || skus.isEmpty()) {
                logger.error("mapFoodToDish 美团返回菜品信息错误,sku信息为空!shopId={},dish={}", shopId, dish);
                throw new RuntimeException("菜品映射失败!美团返回菜品信息错误");
            }
            // 映射外层 dish bean
            DishMapping dishMapping = new DishMapping(dish.getDishId(), dish.getDishId().toString());
            // skuMappings
            List<DishSkuMapping> skuMappings = new ArrayList<DishSkuMapping>();
            skus.forEach((sku) -> {
                // 组装本地保存的菜品entity
                Food food = new Food();
                food.setCategoryId(dish.getDishId());// 将dishId即类别ID存入
                food.setDescription(sku.getDescription());
                food.setName(sku.getDishSkuName());
                food.setFoodId(sku.getDishSkuId().toString());
                food.setIsValid(StatusEnum.ENABLE);
                food.setPrice(new BigDecimal(sku.getPrice() == null ? "0" : sku.getPrice().toString()));
                foods.add(food);
                // 组装映射数据内存sku bean
                DishSkuMapping skuMapping = new DishSkuMapping(sku.getDishSkuId().toString(),
                        sku.getDishSkuId().toString());
                skuMappings.add(skuMapping);
            });
            dishMapping.setWaiMaiDishSkuMappings(skuMappings);
            dishMappings.add(dishMapping);
        });
        logger.info("mapFoodToDish foods = {}", JSON.toJSONString(foods));
        batchSaveFoodInfo(shopId, foods);
        // 请求美团接口进行映射
        String appAuthToken = MeituanConfig.getAppAuthToken(shopId);
        CipCaterTakeoutDishMapRequest request = new CipCaterTakeoutDishMapRequest();
        RequestSysParams sysParams = new RequestSysParams();
        sysParams.setAppAuthToken(appAuthToken);
        sysParams.setSecret(MeituanConfig.getSignkey());
        request.setePoiId(shopId);
        request.setRequestSysParams(sysParams);
        // 设置映射数据
        request.setDishMappings(JSON.toJSONString(dishMappings));
        String result = "";
        try {
            logger.info("mapFoodToDish dishMappings = {}", JSON.toJSONString(dishMappings));
            result = request.doRequest();
            logger.info("mapFoodToDish request result = {}", result);
        } catch (Exception e) {
            logger.error("mapFoodToDish 调用美团接口进行菜品映射失败!shopId={}", shopId, e);
            throw new RuntimeException("调用美团接口进行菜品映射失败,shopId=".concat(shopId));
        }
        if (!Constants.ok.equalsIgnoreCase(result)) {
            logger.error("mapFoodToDish 调用美团接口进行菜品映射失败!shopId={},result={}", shopId, result);
            throw new RuntimeException("调用美团接口进行菜品映射失败,shopId=".concat(shopId).concat(",result=").concat(result));
        }
        logger.info("mapFoodToDish 菜品映射成功");
    }

    /**
     *
     * @Description 映射之前从美团查询店铺所有的菜品
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public List<DishBean> queryAllFoodsFromPlatform(String shopId) {
        String appAuthToken = MeituanConfig.getAppAuthToken(shopId);
        CipCaterTakeoutDishBaseQueryByEPoiIdRequest request = new CipCaterTakeoutDishBaseQueryByEPoiIdRequest();
        RequestSysParams sysParams = new RequestSysParams();
        sysParams.setAppAuthToken(appAuthToken);
        sysParams.setSecret(MeituanConfig.getSignkey());
        request.setePoiId(shopId);
        request.setRequestSysParams(sysParams);
        String result = "";
        try {
            result = request.doRequest();
            logger.info("queryAllFoodsFromPlatform result={}", result);
        } catch (Exception e) {
            logger.error("queryAllFoodsFromPlatform error!shopId={},result={}", shopId, result, e);
            throw new RuntimeException("查询店铺在美团的所有菜品失败!" + e.getMessage());
        }
        if (!StringUtils.hasText(result)) {
            throw new RuntimeException("查询店铺在美团的所有菜品失败!result is empty");
        }
        List<DishBean> dishes = null;
        try {
            dishes = JSONObject.parseObject(result, DishBaseBean.class).getData();
        } catch (Exception e) {
            logger.error("queryAllFoodsFromPlatform error!返回值错误!result={}", result, e);
            throw new RuntimeException("查询店铺在美团的所有菜品失败!返回值错误!");
        }
        if (dishes == null || dishes.isEmpty()) {
            logger.error("queryAllFoodsFromPlatform error!返回值为空,result={}", result);
            throw new RuntimeException("查询店铺在美团的所有菜品失败!返回值为空!");
        }
        return dishes;
    }

    /**
     *
     * @Description 估清菜品
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public void toEstimate(String shopId, Long categoryId, String foodId) {
        updateFoodStock(shopId, categoryId, foodId, 0);
    }

    /**
     *
     * @Description 置满菜品
     * @return
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public void toFull(String shopId, Long categoryId, String foodId) {
        updateFoodStock(shopId, categoryId, foodId, 9999);
    }

    /**
     *
     * @Description 更改库存数量
     * @param shopId
     * @param foodId
     * @param stockNum
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public void updateFoodStock(String shopId, Long categoryId, String foodId, int stockNum) {

        List<DishStock> dishes = new ArrayList<DishStock>();
        DishStock stock = new DishStock();
        stock.seteDishCode(categoryId.toString());
        DishStock.SkuStock sku = new DishStock.SkuStock();
        sku.setSkuId(foodId);
        sku.setStock(stockNum);
        stock.setSkus(Arrays.asList(sku));
        dishes.add(stock);

        CipCaterTakeoutDishStockUpdateRequest request = new CipCaterTakeoutDishStockUpdateRequest();
        RequestSysParams sysParams = new RequestSysParams();
        sysParams.setAppAuthToken(MeituanConfig.getAppAuthToken(shopId));
        sysParams.setSecret(MeituanConfig.getSignkey());
        request.setDishSkuStocks(JSON.toJSONString(dishes));
        request.setePoiId(shopId);
        request.setRequestSysParams(sysParams);
        String result = "";
        try {
            logger.info("updateFoodStock 请求美团修改库存 dishes={}", dishes);
            result = request.doRequest();
            logger.info("updateFoodStock 请求美团修改库存 result={}", result);
        } catch (Exception e) {
            logger.error(
                    "updateFoodStock call meituan error! 调用美团更改菜品库存失败,shopId={},foodId={},stockNum={},requestParams = {}",
                    shopId, foodId, stockNum, dishes, e);
            throw new RuntimeException("调用美团估清菜品失败");
        }
        if (!Constants.ok.equalsIgnoreCase(result)) {
            logger.error("updateFoodStock call meituan error! 调用美团更改菜品库存失败,shopId={},foodId={},stockNum={},result={}",
                    shopId, foodId, stockNum, result);
            throw new RuntimeException("调用美团更改菜品库存失败,result=".concat(result));
        } else {
            Food food = new Food();
            food.setFoodId(foodId);
            food.setShop(new Shop(shopId, Platform.getInstance()));
            food.setCategoryId(categoryId);
            food.setIsValid(stockNum == 0 ? StatusEnum.DISENABLE : StatusEnum.ENABLE);
            JSONObject saveResult = null;
            try {
                logger.info("updateFoodStock 修改菜品状态 bean = {}", food);
                saveResult = jpaClient.saveOrUpdate(JsonFormatUtil.toJSONString(food));
                logger.info("updateFoodStock 修改菜品状态返回值={}", saveResult);
            } catch (Exception e) {
                logger.error("updateFoodStock 修改菜品状态错误,foodId={},categoryId={}", foodId, categoryId);
                throw new RuntimeException("修改菜品状态错误");
            }
        }
        logger.info("toEstimate 调用美团更改菜品库存成功!shopId={},foodId={},stockNum={}", shopId, foodId, stockNum);
    }
}
