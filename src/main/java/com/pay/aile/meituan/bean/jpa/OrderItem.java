package com.pay.aile.meituan.bean.jpa;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderItem implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = -7020083234880666902L;
    // 食物名称
    private String foodName;
    // 份数
    private Integer quantity;
    // 总价
    private BigDecimal totalAmount;
    // 单价
    private BigDecimal price;
    // 订单
    private Order order;

    public String getFoodName() {
        return foodName;
    }

    public Order getOrder() {
        return order;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("OrderItem [foodName=");
        builder.append(foodName);
        builder.append(", quantity=");
        builder.append(quantity);
        builder.append(", totalAmount=");
        builder.append(totalAmount);
        builder.append(", price=");
        builder.append(price);
        builder.append("]");
        return builder.toString();
    }

}
