package com.stock.service.ServiceImpl;

import com.alibaba.fastjson.JSON;
import com.mapper.PermissionMapper;
import com.mapper.UserMapper;
import com.stock.models.Permission;
import com.stock.models.User;
import com.stock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    PermissionMapper permissionMapper;
    @Override
    public User findUserInfo(int id) {
        return userMapper.findUserInfo(id);
    }
    public List<Permission> getMenus(ArrayList<Integer> ids){
        List<Permission> list = permissionMapper.findPermissionByIds(ids);
       return buildTree(list,0);
    }

    @Override
    public List<User> getAllUserByPage(String pageNo, String pageSize) {
        return userMapper.getAllUserByPage(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
    }

    /**
     * 递归所有配置数据
     * @param list 所有数据List
     * @param parentId 开始递归的parentId
     * @return
     */
    public List<Permission> buildTree(List<Permission> list, int parentId){
        List<Permission> trees = new ArrayList<Permission>();
        for (Permission entity : list) {
            int menuId = entity.getId();
            int pid = entity.getParent_id();
            if (parentId == pid) {
                List<Permission> menuLists = buildTree(list, menuId);
                entity.setChildren((ArrayList<Permission>) menuLists);
                trees.add(entity);
            }
        }
        return trees;
    }





}
