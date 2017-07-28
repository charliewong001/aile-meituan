package com.pay.aile.meituan.bean.platform;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @Description: 修改菜品库存bean
 * @see: DishSkuStock 此处填写需要参考的类
 * @version 2017年7月24日 下午4:09:21
 * @author chao.wang
 */
public class DishStock implements Serializable {

    public static class SkuStock implements Serializable {

        /**
         * @author chao.wang
         */
        private static final long serialVersionUID = -183716474933298854L;
        private Integer stock;
        private String skuId;

        public String getSkuId() {
            return skuId;
        }

        public Integer getStock() {
            return stock;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }

        public void setStock(Integer stock) {
            this.stock = stock;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("SkuStock [stock=");
            builder.append(stock);
            builder.append(", skuId=");
            builder.append(skuId);
            builder.append("]");
            return builder.toString();
        }

    }

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = -6579498075559525099L;
    private String eDishCode;
    private List<SkuStock> skus;

    public String geteDishCode() {
        return eDishCode;
    }

    public List<SkuStock> getSkus() {
        return skus;
    }

    public void seteDishCode(String eDishCode) {
        this.eDishCode = eDishCode;
    }

    public void setSkus(List<SkuStock> skus) {
        this.skus = skus;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DishStock [eDishCode=");
        builder.append(eDishCode);
        builder.append(", skus=");
        builder.append(skus);
        builder.append("]");
        return builder.toString();
    }
}
