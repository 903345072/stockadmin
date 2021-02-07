package com.stock.service.ServiceImpl;

import com.mapper.frontend.MemberHeYueApplyMapper;
import com.mapper.frontend.MemberMapper;
import com.stock.service.MemberHeYueApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemberHeYueApplyImpl implements MemberHeYueApply {

    @Autowired
    MemberHeYueApplyMapper memberHeYueApplyMapper;

    @Autowired
    MemberMapper memberMapper;

    @Transactional(rollbackFor=Exception.class)
    @Override
    public int addOneHeYueApply(Map map) {
            int i = memberHeYueApplyMapper.addOneHeYueApply(map);
            int j = memberMapper.decreaseMemberAmount(map);
            if(i<=0 || j<=0){
                return 0;
            }
            return 1;
    }
}
