package com.mapper.frontend;


import com.stock.models.frontend.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {
    Member findMember(String username);
    int decreaseMemberAmount(Map map);
    List findMemberHeYueApplyListByPage(Map map);
}
