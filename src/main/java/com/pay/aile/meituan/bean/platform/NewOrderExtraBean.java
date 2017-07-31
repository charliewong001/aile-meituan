package com.pay.aile.meituan.bean.platform;

import java.io.Serializable;

public class NewOrderExtraBean implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = -7244879192099300103L;
    private Float mt_charge;// float 优惠金额中美团承担的部分 0.5
    private Float poi_charge;// float 优惠金额中商家承担的部分 2
    private Float reduce_fee;// float 活动优惠金额，是美团承担活动费用和商户承担活动费用的总和 2.5
    private String remark;// string 优惠说明 满10元减2.5元
    private Integer type;// int 活动类型 1

    public Float getMt_charge() {
        return mt_charge;
    }

    public Float getPoi_charge() {
        return poi_charge;
    }

    public Float getReduce_fee() {
        return reduce_fee;
    }

    public String getRemark() {
        return remark;
    }

    public Integer getType() {
        return type;
    }

    public void setMt_charge(Float mt_charge) {
        this.mt_charge = mt_charge;
    }

    public void setPoi_charge(Float poi_charge) {
        this.poi_charge = poi_charge;
    }

    public void setReduce_fee(Float reduce_fee) {
        this.reduce_fee = reduce_fee;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("NewOrderExtraBean [mt_charge=");
        builder.append(mt_charge);
        builder.append(", poi_charge=");
        builder.append(poi_charge);
        builder.append(", reduce_fee=");
        builder.append(reduce_fee);
        builder.append(", remark=");
        builder.append(remark);
        builder.append(", type=");
        builder.append(type);
        builder.append("]");
        return builder.toString();
    }

}
