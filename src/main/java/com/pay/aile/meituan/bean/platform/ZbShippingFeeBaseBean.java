package com.pay.aile.meituan.bean.platform;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @Description: 众包配送费base bean
 * @see: ZbShippingFeeBaseBean 此处填写需要参考的类
 * @version 2017年7月28日 下午2:21:01
 * @author chao.wang
 */
public class ZbShippingFeeBaseBean implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = -7181154237876902450L;
    private List<ZbShippingFeeBean> data;

    public List<ZbShippingFeeBean> getData() {
        return data;
    }

    public void setData(List<ZbShippingFeeBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ZbShippingFeeBaseBean [data=" + data + "]";
    }

}
