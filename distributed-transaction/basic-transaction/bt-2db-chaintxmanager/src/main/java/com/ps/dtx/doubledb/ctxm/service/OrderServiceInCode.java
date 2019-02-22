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
    @Qualifier("storageJdbcTemplate")
    private JdbcTemplate storageJdbcTemplate;
    @Autowired
    private PlatformTransactionManager transactionManager;

    private final String CREATE_ORDER_SQL = "INSERT INTO order_tbl(`user_id`,`commodity_code`,`count`,`money`) VALUES (?,?,?,?)";
    private final String UPDATE_STORAGE_SQL = "UPDATE storage_tbl SET count = count - ? WHERE commodity_code = ?";

    public Boolean createOrder(Order order){
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = this.transactionManager.getTransaction(transactionDefinition);
        try{
            int affectedOrderRows = this.orderJdbcTemplate.update(CREATE_ORDER_SQL, order.getUserId(), order.getCommodityCode(), order.getCount(), order.getMoney());
            int affectedStorageRows = this.storageJdbcTemplate.update(UPDATE_STORAGE_SQL, order.getCount(), order.getCommodityCode());
            this.transactionManager.commit(transactionStatus);
            if(affectedOrderRows != 1 || affectedStorageRows != 1){
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
