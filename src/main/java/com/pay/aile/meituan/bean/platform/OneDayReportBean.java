package com.pay.aile.meituan.bean.platform;

import java.io.Serializable;

public class OneDayReportBean implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = 4962331120158415209L;
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
        return "OneDayReportBean [posId=" + posId + ", posType=" + posType + ", erpVersion=" + erpVersion + ", ePoiId="
                + ePoiId + ", ePoiName=" + ePoiName + ", addr=" + addr + ", phone=" + phone + ", longitude=" + longitude
                + ", latitude=" + latitude + "]";
    }

}
