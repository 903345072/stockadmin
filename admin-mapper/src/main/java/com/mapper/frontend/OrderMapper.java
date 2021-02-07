package com.mapper.frontend;

import com.stock.models.frontend.Order;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface OrderMapper {
    int makerOrder(Order order);
}
