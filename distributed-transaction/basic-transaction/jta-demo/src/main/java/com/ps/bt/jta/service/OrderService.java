package com.ps.bt.jta.service;

import org.hibernate.criterion.Order;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OrderService {
    private Set<String> distributedUuidCacheSet = new HashSet<>();
    public void processOrder(Order order){
        String uuid = createOrGetUuid(order);
        if (!this.distributedUuidCacheSet.contains(uuid)){
            handle(order);
            this.distributedUuidCacheSet.add(uuid);
        }        
    }

    private void handle(Order order) {
    }

    private String createOrGetUuid(Order order) {
        return "";
    }
}
