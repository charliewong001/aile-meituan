package com.pay.aile.meituan.bean.jpa;

import java.io.Serializable;
import java.math.BigDecimal;

public class Food implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = -2515545480448265183L;
    // 店铺id
    // 名称
    private String foodId;
    // 店铺
    private Shop shop;
    // 名称
    private String name;
    // 商品描述
    private String description;
    // 图片
    private String picture;
    // 价格
    private BigDecimal price;
    // 状态
    private StatusEnum isValid;

    private String specIds;

    private Long categoryId;// 存放菜品类别ID

    public Long getCategoryId() {
        return categoryId;
    }

    public String getDescription() {
        return description;
    }

    public String getFoodId() {
        return foodId;
    }

    public StatusEnum getIsValid() {
        return isValid;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Shop getShop() {
        return shop;
    }

    public String getSpecIds() {
        return specIds;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public void setIsValid(StatusEnum isValid) {
        this.isValid = isValid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public void setSpecIds(String specIds) {
        this.specIds = specIds;
    }

    @Override
    public String toString() {
        return "Food [foodId=" + foodId + ", shop=" + shop + ", name=" + name + ", description=" + description
                + ", picture=" + picture + ", price=" + price + ", isValid=" + isValid + ", specIds=" + specIds
                + ", categoryId=" + categoryId + "]";
    }

}
