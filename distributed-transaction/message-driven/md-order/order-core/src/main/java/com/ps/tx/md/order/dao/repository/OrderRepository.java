package com.ps.tx.md.order.dao.repository;


import com.ps.tx.md.order.dao.mapper.MdOrderMapper;
import com.ps.tx.md.order.dao.model.MdOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository{
    @Autowired
    private MdOrderMapper mdOrderMapper;

    public List<MdOrder> findByCustomerId(Long customerId){
        return this.mdOrderMapper.selectByCustomerId(customerId);
    }

    public MdOrder findOneByUuid(String uuid){
        return this.mdOrderMapper.selectByUuid(uuid);
    }

    public List<MdOrder> findAll(){
        return this.mdOrderMapper.selectAll();
    }
    public MdOrder addOrder(MdOrder order){
        this.mdOrderMapper.insert(order);
        return order;
    }

    public MdOrder selectById(Long id){
        return this.mdOrderMapper.selectByPrimaryKey(id);
    }
}
