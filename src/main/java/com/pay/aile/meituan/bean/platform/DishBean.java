package com.pay.aile.meituan.bean.platform;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @Description: 菜品分类
 * @see: DishBean 此处填写需要参考的类
 * @version 2017年7月24日 上午11:10:26
 * @author chao.wang
 */
public class DishBean implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = 6240876294942059723L;
    private String eDishCode;// string erp方菜品id
    private Long dishId; // long 美团方菜品id
    private String dishName; // string 菜品名
    private String categoryName; // string 菜品分类名
    private List<DishSkuBean> waiMaiDishSkuBases;// array sku基础信息

    public String getCategoryName() {
        return categoryName;
    }

    public Long getDishId() {
        return dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public String geteDishCode() {
        return eDishCode;
    }

    public List<DishSkuBean> getWaiMaiDishSkuBases() {
        return waiMaiDishSkuBases;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public void seteDishCode(String eDishCode) {
        this.eDishCode = eDishCode;
    }

    public void setWaiMaiDishSkuBases(List<DishSkuBean> waiMaiDishSkuBases) {
        this.waiMaiDishSkuBases = waiMaiDishSkuBases;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DishBean [eDishCode=");
        builder.append(eDishCode);
        builder.append(", dishId=");
        builder.append(dishId);
        builder.append(", dishName=");
        builder.append(dishName);
        builder.append(", categoryName=");
        builder.append(categoryName);
        builder.append(", waiMaiDishSkuBases=");
        builder.append(waiMaiDishSkuBases);
        builder.append("]");
        return builder.toString();
    }

}
