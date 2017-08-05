package com.pay.aile.meituan.bean.platform;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

/**
 *
 * @Description: 门店信息
 * @see: ShopInfoBean 此处填写需要参考的类
 * @version 2017年8月4日 下午5:43:32
 * @author chao.wang
 */
public class ShopInfoBean implements Serializable {

    public static class ShopInfo implements Serializable {

        /**
         * @author chao.wang
         */
        private static final long serialVersionUID = 2068504883098575382L;
        private String ePoiId;// string erp方门店id 最大长度100
        private String name;// string 门店名称
        private String noticeInfo;// string 门店公告信息 2000字以内
        private String address;// string 门店地址
        private String tagName;// string 美团品类名称
        private String latitude;// string 门店经度 38.693203
        private String longitude;// string 门店纬度
        private String phone;// string 门店电话
        private String pictureUrl;// string 门店图片url
        @NotNull(message = "isOpen不能为空")
        private Integer isOpen;// int 是否营业 1 1可配送 3休息中 0-未上线
        @NotNull(message = "isOnline不能为空")
        private Integer isOnline;// int 是否在线 1 1-上线 0-下线
        private String openTime;// string 营业时间 06:00-09:00,15:30-22:00
        private Float shippingFee;// float 配送费
        @NotNull(message = "invoiceSupport不能为空")
        private Integer invoiceSupport;// int 门店是否支持发票 1 1-可开发票 0-不支持
        private Integer invoiceMinPrice;// int 门店支持开发票的最小订单价
        private String invoiceDescription;// string 发票相关说明
        private Integer preBook;// int 是否支持营业时间范围外预下单 1 0-不支持,1 表示支持
        private Integer preBookMinDays;// int 预下单最小日期 0 0-从当天算起
        private Integer preBookMaxDays;// int 预下单最大日期 2 2-后天,
        // 预下单支持从最小日期到最大日期的连续日期。例如：preBookMinDays
        // =0，preBookMaxDays=2，表示支持当天，明天，后天的预下单
        private Integer timeSelect;// int 是否支持营业时间内预下单 1 0-不支持,1 表示支持

        public String getAddress() {
            return address;
        }

        public String getePoiId() {
            return ePoiId;
        }

        public String getInvoiceDescription() {
            return invoiceDescription;
        }

        public Integer getInvoiceMinPrice() {
            return invoiceMinPrice;
        }

        public Integer getInvoiceSupport() {
            return invoiceSupport;
        }

        public Integer getIsOnline() {
            return isOnline;
        }

        public Integer getIsOpen() {
            return isOpen;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public String getName() {
            return name;
        }

        public String getNoticeInfo() {
            return noticeInfo;
        }

        public String getOpenTime() {
            return openTime;
        }

        public String getPhone() {
            return phone;
        }

        public String getPictureUrl() {
            return pictureUrl;
        }

        public Integer getPreBook() {
            return preBook;
        }

        public Integer getPreBookMaxDays() {
            return preBookMaxDays;
        }

        public Integer getPreBookMinDays() {
            return preBookMinDays;
        }

        public Float getShippingFee() {
            return shippingFee;
        }

        public String getTagName() {
            return tagName;
        }

        public Integer getTimeSelect() {
            return timeSelect;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setePoiId(String ePoiId) {
            this.ePoiId = ePoiId;
        }

        public void setInvoiceDescription(String invoiceDescription) {
            this.invoiceDescription = invoiceDescription;
        }

        public void setInvoiceMinPrice(Integer invoiceMinPrice) {
            this.invoiceMinPrice = invoiceMinPrice;
        }

        public void setInvoiceSupport(Integer invoiceSupport) {
            this.invoiceSupport = invoiceSupport;
        }

        public void setIsOnline(Integer isOnline) {
            this.isOnline = isOnline;
        }

        public void setIsOpen(Integer isOpen) {
            this.isOpen = isOpen;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setNoticeInfo(String noticeInfo) {
            this.noticeInfo = noticeInfo;
        }

        public void setOpenTime(String openTime) {
            this.openTime = openTime;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
        }

        public void setPreBook(Integer preBook) {
            this.preBook = preBook;
        }

        public void setPreBookMaxDays(Integer preBookMaxDays) {
            this.preBookMaxDays = preBookMaxDays;
        }

        public void setPreBookMinDays(Integer preBookMinDays) {
            this.preBookMinDays = preBookMinDays;
        }

        public void setShippingFee(Float shippingFee) {
            this.shippingFee = shippingFee;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public void setTimeSelect(Integer timeSelect) {
            this.timeSelect = timeSelect;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("ShopInfo [ePoiId=");
            builder.append(ePoiId);
            builder.append(", name=");
            builder.append(name);
            builder.append(", noticeInfo=");
            builder.append(noticeInfo);
            builder.append(", address=");
            builder.append(address);
            builder.append(", tagName=");
            builder.append(tagName);
            builder.append(", latitude=");
            builder.append(latitude);
            builder.append(", longitude=");
            builder.append(longitude);
            builder.append(", phone=");
            builder.append(phone);
            builder.append(", pictureUrl=");
            builder.append(pictureUrl);
            builder.append(", isOpen=");
            builder.append(isOpen);
            builder.append(", isOnline=");
            builder.append(isOnline);
            builder.append(", openTime=");
            builder.append(openTime);
            builder.append(", shippingFee=");
            builder.append(shippingFee);
            builder.append(", invoiceSupport=");
            builder.append(invoiceSupport);
            builder.append(", invoiceMinPrice=");
            builder.append(invoiceMinPrice);
            builder.append(", invoiceDescription=");
            builder.append(invoiceDescription);
            builder.append(", preBook=");
            builder.append(preBook);
            builder.append(", preBookMinDays=");
            builder.append(preBookMinDays);
            builder.append(", preBookMaxDays=");
            builder.append(preBookMaxDays);
            builder.append(", timeSelect=");
            builder.append(timeSelect);
            builder.append("]");
            return builder.toString();
        }
    }

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = -3720357195109093132L;
    private List<ShopInfo> data;

    public List<ShopInfo> getData() {
        return data;
    }

    public void setData(List<ShopInfo> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ShopInfoBean [data=");
        builder.append(data);
        builder.append("]");
        return builder.toString();
    }
}
