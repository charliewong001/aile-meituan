package com.pay.aile.meituan.bean.platform;

import java.io.Serializable;

/**
 *
 * @Description: 查询订单bean
 * @see: QueryOrderBaseBean 此处填写需要参考的类
 * @version 2017年8月1日 下午4:38:18
 * @author chao.wang
 */
public class QueryOrderBaseBean implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = 7838573595485292719L;

    private NewOrderBean data;

    public NewOrderBean getData() {
        return data;
    }

    public void setData(NewOrderBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("QueryOrderBaseBean [data=");
        builder.append(data);
        builder.append("]");
        return builder.toString();
    }

}
