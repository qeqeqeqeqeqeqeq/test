package com.odianyun.demo.model.VO;

import java.io.Serializable;

public class SoVO implements Serializable {

    private static final long serialVersionUID = 3248775360486836836L;
    private String orderCode;

    private String parentOrderCode;

    private Long userId;

    private String userName;

    private String merchantName;

    private Long storeId;

    private String storeName;

    private Integer orderStatus;

    private String orderRemarkUser;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getParentOrderCode() {
        return parentOrderCode;
    }

    public void setParentOrderCode(String parentOrderCode) {
        this.parentOrderCode = parentOrderCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderRemarkUser() {
        return orderRemarkUser;
    }

    public void setOrderRemarkUser(String orderRemarkUser) {
        this.orderRemarkUser = orderRemarkUser;
    }
}
