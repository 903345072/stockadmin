package com.beta.web.service;


import com.alibaba.fastjson.JSON;
import com.mapper.UserMapper;
import com.stock.models.Permission;
import com.stock.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录专用类,用户登陆时，通过这里查询数据库
 * 自定义类，实现了UserDetailsService接口，用户登录时调用的第一类
 * @author 程就人生
 * @date 2019年5月26日
 */
@Component
public class MyCustomUserService implements UserDetailsService {

    /**
     * 登陆验证时，通过username获取用户的所有权限信息
     * 并返回UserDetails放到spring的全局缓存SecurityContextHolder中，以供授权器使用
     */
    @Autowired
    UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //在这里可以自己调用数据库，对username进行查询，看看在数据库中是否存在
        UserSetter userSetter = new UserSetter();
        User user = userMapper.findUserInfoByName(username);
        if(user == null){
            return null;
        }
        userSetter.setUsername(user.getUsername());
        userSetter.setPassword(user.getPassword());
        userSetter.setId(user.getId());
        ArrayList<Permission> a = user.getRole().getPermission();
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("p1"));
//        authorities.add(new SimpleGrantedAuthority("p2"));
        a.forEach((v)->{
               if(v.getUrl() != null){
                   authorities.add(new SimpleGrantedAuthority(v.getUrl()));
               }
        });
        userSetter.setAuthorities(authorities);
        return userSetter;
    }


}
