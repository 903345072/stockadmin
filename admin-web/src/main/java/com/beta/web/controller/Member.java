package com.beta.web.controller;

import com.stock.service.MemberService;
import com.util.RetResponse;
import com.util.RetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class Member {

    @Autowired
    MemberService MemberServiceImpl;

    @RequestMapping("/getMemberHeYueApplyList")
    public RetResult getAll(@RequestParam Map<String ,Object> map)
    {
        Map<String,Object> data = new HashMap<>();
        List memberHeYueApplyList = MemberServiceImpl.findMemberHeYueApplyList(map);
        int count = MemberServiceImpl.findMemberHeYueApplyCount(map);
        data.put("items",memberHeYueApplyList);
        data.put("count",count);
        return RetResponse.makeOKRsp(data);
    }

    @RequestMapping(value = "/updateHeYueApplyState",method = RequestMethod.POST)
    public RetResult updateHeYueApplyState(@RequestBody Map<String ,Object> map)
    {
        int ret = MemberServiceImpl.updateHeYueApplyState(map);
        if(ret>0){
            return RetResponse.makeOKRsp(ret);
        }
        return RetResponse.makeErRsp();
    }



}
