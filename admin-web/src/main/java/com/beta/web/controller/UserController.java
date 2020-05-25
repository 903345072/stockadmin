package com.beta.web.controller;

import com.stock.service.UserService;
import com.util.RetResponse;
import com.util.RetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.stock.models.User;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService UserServiceImpl;
    @RequestMapping("/getAll")
    public RetResult findUser(@RequestParam Map<String ,Object> map)
    {
        List<User> users = UserServiceImpl.getAllUserByPage((String) map.get("page"), (String) map.get("limit"));
        return RetResponse.makeOKRsp(users);
    }
}
