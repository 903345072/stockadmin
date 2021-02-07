package com.beta.web.controller.FrontendApi;

import com.alibaba.fastjson.JSON;
import com.beta.web.contextHolder.MemberHolder;
import com.beta.web.service.UserSetter;
import com.mapper.frontend.MemberMapper;
import com.stock.models.frontend.Member;
import com.stock.service.*;
import com.stock.service.ServiceImpl.HeYueCaculateAdpterInterface;
import com.util.OrderState;
import com.util.RetResponse;
import com.util.RetResult;
import com.util.YlTrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/frontend")
public class Order {
    @Autowired
    OrderService OrderServiceImpl;
    @RequestMapping(value = "/order/makeOrder",method = RequestMethod.POST)
    public RetResult makeOrder(@Valid @RequestBody com.stock.models.frontend.Order order, BindingResult result)
    {

        int id = MemberHolder.getId();
        order.setMember_id(id);
        order.setStock_status(OrderState.ENTRUSTED.getState());

        //远程下单
        String entrust = (String) YlTrade.entrust();
        Map parse = (Map) JSON.parse(entrust);
        Integer state = (Integer) parse.get("state");
        if (!state.equals(1)){
            return RetResponse.makeErRsp((String) parse.get("message"));
        }

        order.setContract_no((String) parse.get("value"));
        int i = OrderServiceImpl.makerOrder(order);

        return RetResponse.makeOKRsp(parse);
    }

}
