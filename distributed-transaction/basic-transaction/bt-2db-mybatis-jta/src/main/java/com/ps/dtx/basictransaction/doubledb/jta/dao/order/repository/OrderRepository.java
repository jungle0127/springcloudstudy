package com.ps.dtx.basictransaction.doubledb.jta.dao.order.repository;

import com.ps.dtx.basictransaction.doubledb.jta.dao.order.mapper.TOrderMapper;
import com.ps.dtx.basictransaction.doubledb.jta.dao.order.model.TOrder;
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
