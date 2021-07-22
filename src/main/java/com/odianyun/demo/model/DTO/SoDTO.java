package com.odianyun.demo.model.DTO;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SoDTO implements Serializable {
    private static final long serialVersionUID = 7089990858731685761L;
    private String orderCode;

    private String parentOrderCode;

    private Long id;

    private Long merchantId;

    private String code;

    private String userName;

    private Long userId;

    private List<Long> idList;

    private String startOrderCreateTime;

    private String endOrderCreateTime;

    private Integer isOrderRemarkUser;

    private Integer orderStatus;

    private Integer pageNum;

    private Long storeId;

    private Integer pageSize;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public String getStartOrderCreateTime() {
        return startOrderCreateTime;
    }

    public void setStartOrderCreateTime(String startOrderCreateTime) {
        this.startOrderCreateTime = startOrderCreateTime;
    }

    public String getEndOrderCreateTime() {
        return endOrderCreateTime;
    }

    public void setEndOrderCreateTime(String endOrderCreateTime) {
        this.endOrderCreateTime = endOrderCreateTime;
    }

    public Integer getIsOrderRemarkUser() {
        return isOrderRemarkUser;
    }

    public void setIsOrderRemarkUser(Integer isOrderRemarkUser) {
        this.isOrderRemarkUser = isOrderRemarkUser;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
