package com.pay.aile.meituan.bean.platform;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @Description: 新订单bean
 * @see: NewOrderBean 此处填写需要参考的类
 * @version 2017年7月18日 下午2:23:23
 * @author chao.wang
 */
public class NewOrderBean implements Serializable {

    /**
     * @author chao.wang
     */
    private static final long serialVersionUID = 8029162530127564592L;
    @NotNull(message = "orderId不能为空")
    private Long orderId;// long 订单Id 12341234 订单长度最少20位
    private Long orderIdView;// long 订单展示Id 用户下单时看到的订单号
    private String caution;// string 备注 菜多了
    private Long cityId;// long 城市Id 目前暂时用不到此信息
    @NotNull(message = "ctime不能为空")
    private Long ctime;// long 订单创建时间 1476160863 unix_timestamp

    private Long utime;// long 订单更新时间 1476160863
    private String daySeq;// string 门店当天的订单流水号 1 每天流水号从1开始
    @NotNull(message = "deliveryTime不能为空")
    private Long deliveryTime;// long 用户预计送达时间，“立即送达”时为0 单位:秒
    private String ePoiId;// string 三方的门店Id 最大长度100
    @NotNull(message = "hasInvoiced不能为空")
    private Integer hasInvoiced;// int 是否需要发票 0 0-不需要， 1-需要
    private String invoiceTitle;// string 发票抬头
    private Boolean isFavorites;// boolean 用户是否收藏此门店 true
    private Boolean isPoiFirstOrder;// boolean 用户是否第一次在此门店点餐 false
    private Integer isThirdShipping;// int 是否第三方配送 0 0-否，1-是
    private Double latitude;// double 订餐地址纬度
    private Double longitude;// double 订餐地址经度
    private Integer logisticsCode;// int 配送方式码 1002 请查看《7.6补充相关字段说明》
    private Double originalPrice;// double 订单原价
    private Integer payType;// int 支付类型 1 1：货到付款；2：在线支付
    private String poiAddress;// string 门店地址
    private String poiName;// string 门店名称
    private String poiPhone;// string 商家电话
    private String poiReceiveDetail;// string 商家对账详情
    private String recipientAddress;// string 收货人地址 西藏昌都市气象局
    // (西藏昌都市气象局)@#西藏自治区昌都市卡若区城关镇林廓路286号
    // @#符号后是使用经纬度反查的地址
    private String recipientName;// string 收货人姓名
    private String recipientPhone;// string 收货人电话
    private String shipperPhone;// string 配送员电话
    private Double shippingFee;// double 配送费
    @NotNull(message = "status不能为空")
    private Integer status;// int 订单状态 参考附录
    @NotNull(message = "total不能为空")
    private Double total;// double 订单总价 用户实际支付金额
    @NotEmpty(message = "菜品不能为空")
    private List<NewOrderDetailBean> detail;// string 参考【7.3.1
                                            // 根据订单Id查询订单】中的detail

    private List<NewOrderExtraBean> extras;// string 参考【7.3.1
                                           // 根据订单Id查询订单】中的extras字段

    public String getCaution() {
        return caution;
    }

    public Long getCityId() {
        return cityId;
    }

    public Long getCtime() {
        return ctime;
    }

    public String getDaySeq() {
        return daySeq;
    }

    public Long getDeliveryTime() {
        return deliveryTime;
    }

    public List<NewOrderDetailBean> getDetail() {
        return detail;
    }

    public String getePoiId() {
        return ePoiId;
    }

    public List<NewOrderExtraBean> getExtras() {
        return extras;
    }

    public Integer getHasInvoiced() {
        return hasInvoiced;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public Boolean getIsFavorites() {
        return isFavorites;
    }

    public Boolean getIsPoiFirstOrder() {
        return isPoiFirstOrder;
    }

    public Integer getIsThirdShipping() {
        return isThirdShipping;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Integer getLogisticsCode() {
        return logisticsCode;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getOrderIdView() {
        return orderIdView;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public Integer getPayType() {
        return payType;
    }

    public String getPoiAddress() {
        return poiAddress;
    }

    public String getPoiName() {
        return poiName;
    }

    public String getPoiPhone() {
        return poiPhone;
    }

    public String getPoiReceiveDetail() {
        return poiReceiveDetail;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public String getShipperPhone() {
        return shipperPhone;
    }

    public Double getShippingFee() {
        return shippingFee;
    }

    public Integer getStatus() {
        return status;
    }

    public Double getTotal() {
        return total;
    }

    public Long getUtime() {
        return utime;
    }

    public void setCaution(String caution) {
        this.caution = caution;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public void setDaySeq(String daySeq) {
        this.daySeq = daySeq;
    }

    public void setDeliveryTime(Long deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public void setDetail(List<NewOrderDetailBean> detail) {
        this.detail = detail;
    }

    public void setePoiId(String ePoiId) {
        this.ePoiId = ePoiId;
    }

    public void setExtras(List<NewOrderExtraBean> extras) {
        this.extras = extras;
    }

    public void setHasInvoiced(Integer hasInvoiced) {
        this.hasInvoiced = hasInvoiced;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public void setIsFavorites(Boolean isFavorites) {
        this.isFavorites = isFavorites;
    }

    public void setIsPoiFirstOrder(Boolean isPoiFirstOrder) {
        this.isPoiFirstOrder = isPoiFirstOrder;
    }

    public void setIsThirdShipping(Integer isThirdShipping) {
        this.isThirdShipping = isThirdShipping;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLogisticsCode(Integer logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setOrderIdView(Long orderIdView) {
        this.orderIdView = orderIdView;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public void setPoiAddress(String poiAddress) {
        this.poiAddress = poiAddress;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }

    public void setPoiPhone(String poiPhone) {
        this.poiPhone = poiPhone;
    }

    public void setPoiReceiveDetail(String poiReceiveDetail) {
        this.poiReceiveDetail = poiReceiveDetail;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public void setShipperPhone(String shipperPhone) {
        this.shipperPhone = shipperPhone;
    }

    public void setShippingFee(Double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setUtime(Long utime) {
        this.utime = utime;
    }

    @Override
    public String toString() {
        return "NewOrderBean [orderId=" + orderId + ", orderIdView=" + orderIdView + ", caution=" + caution
                + ", cityId=" + cityId + ", ctime=" + ctime + ", utime=" + utime + ", daySeq=" + daySeq
                + ", deliveryTime=" + deliveryTime + ", ePoiId=" + ePoiId + ", hasInvoiced=" + hasInvoiced
                + ", invoiceTitle=" + invoiceTitle + ", isFavorites=" + isFavorites + ", isPoiFirstOrder="
                + isPoiFirstOrder + ", isThirdShipping=" + isThirdShipping + ", latitude=" + latitude + ", longitude="
                + longitude + ", logisticsCode=" + logisticsCode + ", originalPrice=" + originalPrice + ", payType="
                + payType + ", poiAddress=" + poiAddress + ", poiName=" + poiName + ", poiPhone=" + poiPhone
                + ", poiReceiveDetail=" + poiReceiveDetail + ", recipientAddress=" + recipientAddress
                + ", recipientName=" + recipientName + ", recipientPhone=" + recipientPhone + ", shipperPhone="
                + shipperPhone + ", shippingFee=" + shippingFee + ", status=" + status + ", total=" + total
                + ", detail=" + detail + ", extras=" + extras + "]";
    }

}
