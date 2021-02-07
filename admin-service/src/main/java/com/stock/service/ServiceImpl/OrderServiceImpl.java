package com.stock.service.ServiceImpl;

import com.mapper.frontend.OrderMapper;
import com.stock.models.frontend.Order;
import com.stock.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Override
    public int makerOrder(Order order) {
        return orderMapper.makerOrder(order);
    }
}
