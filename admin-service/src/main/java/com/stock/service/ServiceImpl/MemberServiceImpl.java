package com.stock.service.ServiceImpl;


import com.mapper.frontend.MemberHeYueApplyMapper;
import com.mapper.frontend.MemberMapper;
import com.stock.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberHeYueApplyMapper memberHeYueApplyMapper;
    @Override
    public List findMemberHeYueApplyList(Map map) {
        return memberHeYueApplyMapper.findMemberHeYueApplyListByPage(map);
    }

    @Override
    public int findMemberHeYueApplyCount(Map map) {
        return memberHeYueApplyMapper.findMemberHeYueApplyCount(map);
    }

    @Override
    public int updateHeYueApplyState(Map map) {
        return memberHeYueApplyMapper.updateHeYueApplyState(map);
    }

    @Override
    public List selectMemberHeYueByCase(Map map) {
        return memberHeYueApplyMapper.selectMemberHeYueByCase(map);
    }
}
