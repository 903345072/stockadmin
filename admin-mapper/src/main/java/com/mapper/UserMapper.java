package com.mapper;

import com.stock.models.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    User findUserInfo(int id);
    User findUserInfoByName(String username);
    List<User> getAllUserByPage(@Param("currPage")Integer pageNo, @Param("pageSize")Integer pageSize);
}
