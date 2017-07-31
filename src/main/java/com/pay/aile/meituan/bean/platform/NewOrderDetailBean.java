package com.pay.aile.meituan.bean.platform;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 *
 * @Description: 订单菜品详情
 * @see: OrderDetailBean 此处填写需要参考的类
 * @version 2017年7月14日 下午4:57:57
 * @author chao.wang
 */
public class NewOrderDetailBean implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = 280103598505899555L;

    private String app_food_code;// erp方菜品id 等价于eDishCode

    private Integer box_num;// 餐盒数量 2 餐盒总数量，例如一份菜A需要1个餐盒，订单中点了2份菜A，餐盒数量为2

    private Float box_price;// 餐盒单价 1
    private String food_name;// 菜品名
    @NotNull(message = "price不能为空")
    private Float price;// 价格 菜品原价
    private String sku_id;// erp方菜品sku
    private String quantity;// 菜品份数
    private String unit;// 单位 份
    private Float food_discount;// 菜品折扣 0.8//
                                // 只是美团商家、APP方配送的门店才会设置，默认为1。折扣值不参与总价计算。
    private String food_property;// 菜品属性 "中辣,微甜" 多个属性用英文逗号隔开
    private Integer cart_id;// 商品所在的口袋 1 0为1号口袋，1为2号口袋，以此类推

    public String getApp_food_code() {
        return app_food_code;
    }

    public Integer getBox_num() {
        return box_num;
    }

    public Float getBox_price() {
        return box_price;
    }

    public Integer getCart_id() {
        return cart_id;
    }

    public Float getFood_discount() {
        return food_discount;
    }

    public String getFood_name() {
        return food_name;
    }

    public String getFood_property() {
        return food_property;
    }

    public Float getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getSku_id() {
        return sku_id;
    }

    public String getUnit() {
        return unit;
    }

    public void setApp_food_code(String app_food_code) {
        this.app_food_code = app_food_code;
    }

    public void setBox_num(Integer box_num) {
        this.box_num = box_num;
    }

    public void setBox_price(Float box_price) {
        this.box_price = box_price;
    }

    public void setCart_id(Integer cart_id) {
        this.cart_id = cart_id;
    }

    public void setFood_discount(Float food_discount) {
        this.food_discount = food_discount;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public void setFood_property(String food_property) {
        this.food_property = food_property;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("NewOrderDetailBean [app_food_code=");
        builder.append(app_food_code);
        builder.append(", box_num=");
        builder.append(box_num);
        builder.append(", box_price=");
        builder.append(box_price);
        builder.append(", food_name=");
        builder.append(food_name);
        builder.append(", price=");
        builder.append(price);
        builder.append(", sku_id=");
        builder.append(sku_id);
        builder.append(", quantity=");
        builder.append(quantity);
        builder.append(", unit=");
        builder.append(unit);
        builder.append(", food_discount=");
        builder.append(food_discount);
        builder.append(", food_property=");
        builder.append(food_property);
        builder.append(", cart_id=");
        builder.append(cart_id);
        builder.append("]");
        return builder.toString();
    }

}
