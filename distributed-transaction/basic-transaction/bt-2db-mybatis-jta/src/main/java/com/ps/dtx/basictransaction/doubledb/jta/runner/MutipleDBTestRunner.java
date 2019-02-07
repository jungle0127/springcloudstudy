package com.ps.dtx.basictransaction.doubledb.jta.runner;

import com.ps.dtx.basictransaction.doubledb.jta.dao.order.model.TOrder;
import com.ps.dtx.basictransaction.doubledb.jta.dao.order.repository.OrderRepository;
import com.ps.dtx.basictransaction.doubledb.jta.dao.storage.model.TStorage;
import com.ps.dtx.basictransaction.doubledb.jta.dao.storage.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
public class MutipleDBTestRunner implements CommandLineRunner {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private StorageRepository storageRepository;
    @Override
    public void run(String... args) throws Exception {
        this.addData();
    }
    @Transactional
    public void getData(){
        List<TOrder> orderList = this.orderRepository.getAllOrders();
        for(TOrder pojo: orderList){
            System.out.println(pojo.toString());
        }
        List<TStorage> storageList = this.storageRepository.getAll();
        for(TStorage pojo: storageList){
            System.out.println(pojo.toString());
        }
    }
    @Transactional
    public void addData(){
        TOrder order = new TOrder();
        order.setAmount(5);
        order.setCreateTime(new Date());
        order.setCustomerId(1L);
        order.setOrderDesc("bt-2db-jta");
        order.setStorageId(1L);
        TStorage storage = new TStorage();
        storage.setCreateTime(new Date());
        storage.setInventory(500);
        storage.setProductName("bt-2db-jta");
        storage.setUpdateTime(new Date());
        Integer affectedRows = this.orderRepository.addOrder(order);
        Integer resultRows = this.storageRepository.addStorage(storage);
    }
}
