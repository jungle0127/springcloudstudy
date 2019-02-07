package com.ps.dtx.doubledb.ctxm.service;

import com.ps.dtx.doubledb.ctxm.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class OrderServiceInCode {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    @Qualifier("orderJdbcTemplate")
    private JdbcTemplate orderJdbcTemplate;
    @Autowired
    @Qualifier("storeageJdbcTemplate")
    private JdbcTemplate storeageJdbcTemplate;
    @Autowired
    private PlatformTransactionManager transactionManager;

    private final String CREATE_ORDER_SQL = "INSERT INTO t_order(`customer_id`,`storage_id`,`amount`,`order_desc`) VALUES (?,?,?,?)";
    private final String UPDATE_STOREAGE_SQL = "UPDATE t_storage SET inventory = inventory - ?, update_time = now() WHERE id = ?";

    public Boolean createOrder(Order order){
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = this.transactionManager.getTransaction(transactionDefinition);
        try{
            int affectedOrderRows =  this.orderJdbcTemplate.update(CREATE_ORDER_SQL,order.getCustomerId(),order.getStorageId(), order.getAmount(),order.getOrderDesc());
            int affectedStoreageRows = this.storeageJdbcTemplate.update(UPDATE_STOREAGE_SQL, order.getAmount(),order.getStorageId());
            this.transactionManager.commit(transactionStatus);
            if(affectedOrderRows != 1 || affectedStoreageRows != 1){
                return false;
            }
        } catch (Exception e){
            logger.error("create order failed with exception: {}",e.getMessage());
            this.transactionManager.rollback(transactionStatus);
            throw e;
        }
        return true;
    }
}
