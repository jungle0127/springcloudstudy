package com.ps.dtx.basictransaction.doubledb.jta.dao.order.model;

import java.util.Date;

public class TOrder {
    private Long id;

    private Long customerId;

    private Long storageId;

    private Integer amount;

    private String orderDesc;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc == null ? null : orderDesc.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TOrder{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", storageId=" + storageId +
                ", amount=" + amount +
                ", orderDesc='" + orderDesc + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}