package com.ps.dtx.md.facade.model;

public class OrderDTO {
    private String userId;
    private String commodityCode;
    private int orderCount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "userId='" + userId + '\'' +
                ", commodityCode='" + commodityCode + '\'' +
                ", orderCount=" + orderCount +
                '}';
    }
}
