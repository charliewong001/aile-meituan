package com.pay.aile.meituan.bean.push;

import java.io.Serializable;

/**
 *
 * @Description: 向POS端推送的bean
 * @see: PushOrderItem 此处填写需要参考的类
 * @version 2017年7月19日 下午4:33:48
 * @author chao.wang
 */
public class PushNewOrderItem implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = 2766259675835638535L;

    private String foodName;
    private String price;
    private String quantity;
    private String totalAmount;

    public String getFoodName() {
        return foodName;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "PushOrderItem [foodName=" + foodName + ", price=" + price + ", quantity=" + quantity + ", totalAmount="
                + totalAmount + "]";
    }
}
