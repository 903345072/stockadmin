package com.mapper.frontend;

import com.stock.models.frontend.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberHeYueApplyMapper {
    int addOneHeYueApply(Map map);
    List findMemberHeYueApplyListByPage(Map map);
    int findMemberHeYueApplyCount(Map map);
    int updateHeYueApplyState(Map map);
    List selectMemberHeYueByCase(Map map);
}
