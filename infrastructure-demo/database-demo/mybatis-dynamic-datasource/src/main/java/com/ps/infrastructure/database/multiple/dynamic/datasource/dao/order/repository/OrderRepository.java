package com.ps.infrastructure.database.multiple.dynamic.datasource.dao.order.repository;

import com.ps.infrastructure.database.multiple.dynamic.datasource.dao.order.mapper.TOrderMapper;
import com.ps.infrastructure.database.multiple.dynamic.datasource.dao.order.model.TOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {
    @Autowired
    private TOrderMapper orderMapper;

    public Integer addOrder(TOrder order){
        return this.orderMapper.insert(order);
    }
    public List<TOrder> getAllOrders(){
        return this.orderMapper.selectAll();
    }
}
