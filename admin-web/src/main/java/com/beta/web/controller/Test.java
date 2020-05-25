package com.beta.web.controller;


import com.alibaba.fastjson.JSON;
import com.stock.models.Permission;
import com.stock.models.User;

import com.stock.service.PermissionService;
import com.stock.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class Test {

    @Autowired
    User user;

    @Autowired
    PermissionService PermissionServiceImpl;

    @Autowired
    UserService UserServiceImpl;

    @RequestMapping("/test")
    public void echo()
    {
        Permission pm = new Permission();
        pm.setComponent("a");
        pm.setParent_id(0);
        pm.setPath("/a");
        pm.setTitle("dd");
        PermissionServiceImpl.insertOnePermission(pm);
        System.out.println(pm.getId());
    }

    @RequestMapping("/getUserInfo")
    public HashMap<Object,Object> getUserInfo()
    {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String,Object> userInfo = (HashMap<String,Object>) authentication.getPrincipal();
        int id = (int)userInfo.get("id");
        User user = UserServiceImpl.findUserInfo(id);
        List<Object> lists = new ArrayList<>();
        HashMap<Object,Object> hm = new HashMap<>();
        hm.put("roles", user.getRole().getRole_name());
        hm.put("name", user.getUsername());
        hm.put("avatar", new java.lang.String("https://pic4.zhimg.com/v2-97dea6d0e3c7378dccb10d41baa992f7_1200x500.jpg"));
        hm.put("introduction", new java.lang.String("你爹来辣"));
        HashMap<Object,Object> route = new HashMap<>();
        ArrayList<Permission> a = user.getRole().getPermission();
        ArrayList<Integer> ids = new ArrayList<>();
        a.forEach((v)->{
            ids.add(v.getId());
        });
        hm.put("routelist", UserServiceImpl.getMenus(ids));
        lists.add(hm);
        HashMap<Object,Object> hm1 = new HashMap<>();
        hm1.put("data",lists);
        hm1.put("code",200);
        return  hm1;
    }
}
