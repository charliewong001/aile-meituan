package com.pay.aile.meituan.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.pay.aile.meituan.sdk.MeituanConfig;
import com.pay.aile.meituan.util.HttpClientUtil;
import com.pay.aile.meituan.util.SignUtil;

/**
 *
 * @Description: 心跳上报
 * @see: HeartBeatService 此处填写需要参考的类
 * @version 2017年7月14日 下午4:30:52
 * @author chao.wang
 */
@Service
public class HeartbeatService {

    private class OneDayReportBean implements Serializable {

        /**
         * @author chao.wang
         */
        private static final long serialVersionUID = -3983661222912759382L;
        private String posId;// string 是 终端唯一标识
        private String posType;// int 是 终端类型 1：云端 2：pos 3：服务员app 4：pad 5：微信端
                               // 6：划菜机 7：点菜机
        private String erpVersion;// string 是 软件版本号
        private String ePoiId;// string 是 ERP厂商门店id
        private String ePoiName;// string 是 ERP商家门店名
        private String addr;// string 是 地址
        private String phone;// string 是 电话
        private String longitude;// float 否 经度，±180之间，可以保留两位小数，东经为正，西经为负
        private String latitude;// float 否 纬度，±180之间，可以保留两位小数，北纬为正，南纬为负

        public String getAddr() {
            return addr;
        }

        public String getePoiId() {
            return ePoiId;
        }

        public String getePoiName() {
            return ePoiName;
        }

        public String getErpVersion() {
            return erpVersion;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public String getPhone() {
            return phone;
        }

        public String getPosId() {
            return posId;
        }

        public String getPosType() {
            return posType;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public void setePoiId(String ePoiId) {
            this.ePoiId = ePoiId;
        }

        public void setePoiName(String ePoiName) {
            this.ePoiName = ePoiName;
        }

        public void setErpVersion(String erpVersion) {
            this.erpVersion = erpVersion;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setPosId(String posId) {
            this.posId = posId;
        }

        public void setPosType(String posType) {
            this.posType = posType;
        }

        @Override
        public String toString() {
            return "OneDayReportBean [posId=" + posId + ", posType=" + posType + ", erpVersion=" + erpVersion
                    + ", ePoiId=" + ePoiId + ", ePoiName=" + ePoiName + ", addr=" + addr + ", phone=" + phone
                    + ", longitude=" + longitude + ", latitude=" + latitude + "]";
        }

    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${meituan_heartbeat_url}")
    private String heartbeatUrl;// 30s上报地址
    @Value("${meituan_heartbeat_data_url}")
    private String heartbeatDataUrl;// 24h上报地址

    /**
     *
     * @Description 30s心跳上报
     * @param shopIds
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public void halfSecReport(String[] shopIds) {
        if (shopIds == null || shopIds.length == 0) {
            return;
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("developerId", MeituanConfig.getDeveloperId());
        data.put("time", System.currentTimeMillis());
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (String shopId : shopIds) {
            // TODO 通过shopId获取deviceNo设备号
            String deviceNo = "";
            Map<String, String> item = new HashMap<String, String>();
            item.put("ePoiId", shopId);
            item.put("posId", deviceNo);
            list.add(item);
        }
        data.put("list", list);

        // 创建签名
        String sign = SignUtil.createSign(MeituanConfig.getSignkey(), data);
        data.put("sign", sign);

        String result = "";
        try {
            logger.info("halfSecReport requestData={}", data);
            result = HttpClientUtil.sendRequestMap(HttpMethod.POST, heartbeatUrl, "http", data, "utf-8");
        } catch (Exception e) {
            logger.error("halfSecReport error!time={},result={}", new Date(), result, e);
        }
    }

    /**
     *
     * @Description 24小时数据上报
     * @see 需要参考的类或方法
     * @author chao.wang
     */
    public void oneDayReport() {
        // TODO 查询所有美团门店信息
        // TODO 获取应上报总数
        int total = 0;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("developerId", MeituanConfig.getDeveloperId());
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("total", total);
        List<OneDayReportBean> list = new ArrayList<OneDayReportBean>();
        // TODO 分页查询数据并设置到list中
        data.put("list", list);
        params.put("data", JSON.toJSONString(data));
        String sign = SignUtil.createSign(MeituanConfig.getSignkey(), params);
        params.put("sign", sign);
        String result = "";
        try {
            result = HttpClientUtil.sendRequestMap(HttpMethod.POST, heartbeatDataUrl, "http", params, "utf-8");
        } catch (Exception e) {
            logger.error("oneDayReport error!time={},result={}", new Date(), result, e);
        }
    }
}
