package com.pay.aile.meituan.bean.platform;

import java.io.Serializable;
import java.util.List;

public class DishBaseBean implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = 7414607698461602280L;

    private List<DishBean> data;

    public List<DishBean> getData() {
        return data;
    }

    public void setData(List<DishBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DishBaseBean [data=" + data + "]";
    }

}
