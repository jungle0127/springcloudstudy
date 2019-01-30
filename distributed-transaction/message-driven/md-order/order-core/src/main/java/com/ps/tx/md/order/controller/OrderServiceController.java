package com.ps.tx.md.order.controller;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import com.ps.tx.md.order.IOrderService;
import com.ps.tx.md.order.dao.model.MdOrder;
import com.ps.tx.md.order.dao.repository.OrderRepository;
import com.ps.tx.md.order.model.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderServiceController implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private JmsTemplate jmsTemplate;

    private TimeBasedGenerator uuidGenerator = Generators.timeBasedGenerator();

    /**
     * 将新订单发送到order:new队列，由ticketservice响应
     *
     * @param dto
     */
    @PostMapping("")
    public void createOrder(@RequestBody OrderDTO dto) {
        dto.setUuid(this.uuidGenerator.generate().toString());
        this.jmsTemplate.convertAndSend("order:new", dto);
    }

    @GetMapping("")
    public List<MdOrder> getAllOrders() {
        return this.orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public OrderDTO getOrderProxy(@PathVariable Long id) {
        MdOrder order = this.orderRepository.selectById(id);
        OrderDTO dto = new OrderDTO();
        dto.setAmount(order.getAmount());
        dto.setDetail(order.getStatus());
        dto.setId(order.getId());
        dto.setTitle(order.getTitle());
        return null;
    }
}
