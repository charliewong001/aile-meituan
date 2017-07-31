package com.pay.aile.meituan.bean.platform;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @Description: 菜品映射Bean
 * @see: DishMapping 此处填写需要参考的类
 * @version 2017年7月24日 下午2:43:52
 * @author chao.wang
 */
public class DishMapping implements Serializable {

    public static class DishSkuMapping implements Serializable {

        /**
         * @author chao.wang
         */
        private static final long serialVersionUID = 5816397629842757988L;

        private String eDishSkuCode;// ERP方菜品ID
        private String dishSkuId;// 美团方菜品ID

        public DishSkuMapping() {
        }

        public DishSkuMapping(String eDishSkuCode, String dishSkuId) {
            super();
            this.eDishSkuCode = eDishSkuCode;
            this.dishSkuId = dishSkuId;
        }

        public String getDishSkuId() {
            return dishSkuId;
        }

        public String geteDishSkuCode() {
            return eDishSkuCode;
        }

        public void setDishSkuId(String dishSkuId) {
            this.dishSkuId = dishSkuId;
        }

        public void seteDishSkuCode(String eDishSkuCode) {
            this.eDishSkuCode = eDishSkuCode;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("DishSkuMapping [eDishSkuCode=");
            builder.append(eDishSkuCode);
            builder.append(", dishSkuId=");
            builder.append(dishSkuId);
            builder.append("]");
            return builder.toString();
        }

    }

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = 4808980710691634921L;// 美团方菜品ID
    private Long dishId;// ERP方菜品ID
    private List<DishSkuMapping> waiMaiDishSkuMappings;
    private String eDishCode;

    public DishMapping() {
    }

    public DishMapping(Long dishId, String eDishCode) {
        this.dishId = dishId;
        this.eDishCode = eDishCode;
    }

    public Long getDishId() {
        return dishId;
    }

    public String geteDishCode() {
        return eDishCode;
    }

    public List<DishSkuMapping> getWaiMaiDishSkuMappings() {
        return waiMaiDishSkuMappings;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public void seteDishCode(String eDishCode) {
        this.eDishCode = eDishCode;
    }

    public void setWaiMaiDishSkuMappings(List<DishSkuMapping> waiMaiDishSkuMappings) {
        this.waiMaiDishSkuMappings = waiMaiDishSkuMappings;
    }

    @Override
    public String toString() {
        return "DishMapping [dishId=" + dishId + ", waiMaiDishSkuMappings=" + waiMaiDishSkuMappings + ", eDishCode="
                + eDishCode + "]";
    }

}
