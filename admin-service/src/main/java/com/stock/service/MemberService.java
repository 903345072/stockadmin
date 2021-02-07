package com.stock.service;

import java.util.List;
import java.util.Map;

public interface MemberService {

    List findMemberHeYueApplyList(Map map);
    int findMemberHeYueApplyCount(Map map);
    int updateHeYueApplyState(Map map);
    List selectMemberHeYueByCase(Map map);
}
