package com.pay.aile.meituan.bean.platform;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @Description: 这里用一句话描述这个类的作用
 * @see: DishBaseBean 此处填写需要参考的类
 * @version 2017年7月26日 下午5:49:17
 * @author chao.wang
 */
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
