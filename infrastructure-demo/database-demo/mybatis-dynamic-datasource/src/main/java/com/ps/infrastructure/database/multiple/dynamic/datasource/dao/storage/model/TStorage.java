package com.ps.infrastructure.database.multiple.dynamic.datasource.dao.storage.model;

import java.util.Date;

public class TStorage {
    private Long id;

    private String productName;

    private Integer inventory;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "TStorage{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", inventory=" + inventory +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}