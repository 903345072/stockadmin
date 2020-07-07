package com.beta.web.controller;


import com.alibaba.fastjson.JSON;
import com.stock.models.Permission;
import com.stock.models.Role;
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
    PermissionService PermissionServiceImpl;

    @Autowired
    UserService UserServiceImpl;

    @RequestMapping("/testd")
    public void echo()
    {

        System.out.println(333);
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
        hm.put("roles", "admins");
        hm.put("name", user.getUsername());
        hm.put("avatar", new java.lang.String("https://pic4.zhimg.com/v2-97dea6d0e3c7378dccb10d41baa992f7_1200x500.jpg"));
        hm.put("introduction", new java.lang.String("你爹来辣"));
        HashMap<Object,Object> route = new HashMap<>();
        ArrayList<Permission> permissionArrayList = new ArrayList<>();
        for (Role role : user.getRole()){
            for (Permission permission: role.getPermission()){
                permissionArrayList.add(permission);
            }
        }
        ArrayList<Integer> ids = new ArrayList<>();
        permissionArrayList.forEach((v)->{
            ids.add(v.getId());
        });
        if (ids.size()>0){
            hm.put("routelist", UserServiceImpl.getMenus(ids));
        }else {
            hm.put("routelist", new ArrayList<>());
        }

        lists.add(hm);
        HashMap<Object,Object> hm1 = new HashMap<>();
        hm1.put("data",lists);
        hm1.put("code",200);
        return  hm1;
    }
}
