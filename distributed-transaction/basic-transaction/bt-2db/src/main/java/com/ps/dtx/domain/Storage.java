package com.ps.dtx.domain;

import java.util.Date;

public class Storage {
    private Integer id;
    private String commodityCode;
    private Integer count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", commodityCode='" + commodityCode + '\'' +
                ", count=" + count +
                '}';
    }
}
