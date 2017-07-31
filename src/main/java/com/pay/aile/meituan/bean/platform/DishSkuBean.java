package com.pay.aile.meituan.bean.platform;

import java.io.Serializable;

/**
 *
 * @Description: 菜品信息
 * @see: DishSkuBean 此处填写需要参考的类
 * @version 2017年7月24日 上午11:13:54
 * @author chao.wang
 */
public class DishSkuBean implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = 7729810874774385156L;

    private Long dishSkuId;// long 美团方菜品sku id
    private String dishSkuName;// string 菜品sku名称，通常跟菜品dishName一致
    private String eDishSkuCode;// string erp方菜品sku id
    private String spec;// string sku规格 大份
    private String description;// string 描述 大份
    private Float price;// float 价格 12

    public String getDescription() {
        return description;
    }

    public Long getDishSkuId() {
        return dishSkuId;
    }

    public String getDishSkuName() {
        return dishSkuName;
    }

    public String geteDishSkuCode() {
        return eDishSkuCode;
    }

    public Float getPrice() {
        return price;
    }

    public String getSpec() {
        return spec;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDishSkuId(Long dishSkuId) {
        this.dishSkuId = dishSkuId;
    }

    public void setDishSkuName(String dishSkuName) {
        this.dishSkuName = dishSkuName;
    }

    public void seteDishSkuCode(String eDishSkuCode) {
        this.eDishSkuCode = eDishSkuCode;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DishSkuBean [dishSkuId=");
        builder.append(dishSkuId);
        builder.append(", dishSkuName=");
        builder.append(dishSkuName);
        builder.append(", eDishSkuCode=");
        builder.append(eDishSkuCode);
        builder.append(", spec=");
        builder.append(spec);
        builder.append(", description=");
        builder.append(description);
        builder.append(", price=");
        builder.append(price);
        builder.append("]");
        return builder.toString();
    }

}
