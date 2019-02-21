package com.ps.dtx.md.account.model;

import java.io.Serializable;

public class Account implements Serializable {
    private String userId;
    private Integer money;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "userId='" + userId + '\'' +
                ", money=" + money +
                '}';
    }
}
