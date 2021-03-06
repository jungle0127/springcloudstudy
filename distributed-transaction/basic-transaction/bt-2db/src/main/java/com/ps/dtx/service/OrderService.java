package com.ps.dtx.service;

import com.ps.dtx.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    @Qualifier("orderJdbcTemplate")
    private JdbcTemplate orderJdbcTemplate;
    @Autowired
    @Qualifier("storageJdbcTemplate")
    private JdbcTemplate storageJdbcTemplate;

    private final String CREATE_ORDER_SQL = "INSERT INTO order_tbl(`user_id`,`commodity_code`,`count`,`money`) VALUES (?,?,?,?)";
    private final String UPDATE_STORAGE_SQL = "UPDATE storage_tbl SET count = count - ? WHERE commodity_code = ?";

    @Transactional
    public Boolean createOrder(Order order) {
        int affectedOrderRows = this.orderJdbcTemplate.update(CREATE_ORDER_SQL, order.getUserId(), order.getCommodityCode(), order.getCount(), order.getMoney());
        int affectedStoreageRows = this.storageJdbcTemplate.update(UPDATE_STORAGE_SQL, order.getCount(), order.getCommodityCode());
        if (affectedOrderRows != 1 || affectedStoreageRows != 1) {
            return false;
        }
        if (affectedOrderRows == 1) {
            throw new RuntimeException("pseudo exception.");
        }
        return true;
    }
}
