package com.ps.dtx.jta.service;

import com.ps.dtx.jta.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceAnnotation {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    @Qualifier("orderJdbcTemplate")
    private JdbcTemplate orderJdbcTemplate;
    @Autowired
    @Qualifier("storageJdbcTemplate")
    private JdbcTemplate storageJdbcTemplate;

    private final String CREATE_ORDER_SQL = "INSERT INTO t_order(`customer_id`,`storage_id`,`amount`,`order_desc`) VALUES (?,?,?,?)";
    private final String UPDATE_STOREAGE_SQL = "UPDATE t_storage SET inventory = inventory - ?, update_time = now() WHERE id = ?";

    @Transactional
    public Boolean createOrder(Order order){
        try{
            int affectedOrderRows =  this.orderJdbcTemplate.update(CREATE_ORDER_SQL,order.getCustomerId(),order.getStorageId(), order.getAmmount(),order.getOrderDesc());
            int affectedStoreageRows = this.storageJdbcTemplate.update(UPDATE_STOREAGE_SQL, order.getAmmount(),order.getStorageId());
            if(affectedOrderRows != 1 || affectedStoreageRows != 1){
                return false;
            }
//            throw new RuntimeException("pseudo exception");
        } catch (Exception e){
            logger.error("create order failed with exception: {}",e.getMessage());
            throw e;
        }
        return true;
    }
}
