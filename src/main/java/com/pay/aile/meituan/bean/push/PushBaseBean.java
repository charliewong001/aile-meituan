package com.pay.aile.meituan.bean.push;

import java.io.Serializable;

/**
 *
 * @Description: 这里用一句话描述这个类的作用
 * @see: PushBaseBean 此处填写需要参考的类
 * @version 2017年7月28日 上午10:14:42
 * @author chao.wang
 */
public class PushBaseBean implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = -1860128306684393923L;
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PushBaseBean [id=");
        builder.append(id);
        builder.append("]");
        return builder.toString();
    }

}
