package com.beta.web.controller.FrontendApi;

import com.alibaba.fastjson.JSON;
import com.beta.web.contextHolder.MemberHolder;
import com.beta.web.service.UserSetter;
import com.mapper.frontend.MemberMapper;
import com.stock.models.frontend.Member;
import com.stock.service.*;

import com.stock.service.ServiceImpl.HeYueCaculateAdpterInterface;
import com.util.RetResponse;
import com.util.RetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/frontend")
public class HeYueApi {
    @Autowired
    @Qualifier("HeYueCaculateAdper")
    HeYueCaculateAdpterInterface HeYueCaculateImpl;

    @Autowired
    InterestService InterestImpl;

    @Autowired
    HeYueService HeYueImpl;

    @Autowired
    LeverageService LeverageImpl;

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    MemberHeYueApply MemberHeYueApplyImpl;

    @Autowired
    MemberService MemberServiceImpl;

    @RequestMapping(value = "/heyue/caculate",method = RequestMethod.GET)
    public RetResult getAll(@RequestParam(value = "deposit") double deposit,@RequestParam(value = "heyue_id") int heyue_id, @RequestParam(value = "leverage_id") int leverage_id)
    {
        HashMap<String,Object> hm = new HashMap<>();
        double leverage_money = HeYueCaculateImpl.cal_leverage_money(deposit, leverage_id);
        double total_capitial = HeYueCaculateImpl.cal_total_capitial(deposit,leverage_money);
        double deposit_ = HeYueCaculateImpl.cal_deposit(deposit);
        double loss_warning_line = HeYueCaculateImpl.cal_loss_warning_line(deposit,0.5,leverage_money);
        double loss_sell_line = HeYueCaculateImpl.cal_loss_sell_line(deposit,0.2, leverage_money);
        double interest_rate = HeYueCaculateImpl.cal_interest_rate(heyue_id,leverage_id);
        int capitial_used_time = HeYueCaculateImpl.cal_capitial_used_time(heyue_id);
        double interest = HeYueCaculateImpl.cal_interest(interest_rate,leverage_money,capitial_used_time);
        double repare_capitical = HeYueCaculateImpl.cal_repare_capitical(deposit,interest);
        double leverageName = HeYueCaculateImpl.getLeverageName(leverage_id);
        hm.put("leverage_money",leverage_money);
        hm.put("total_capitial",total_capitial);
        hm.put("deposit",deposit_);
        hm.put("loss_warning_line",loss_warning_line);
        hm.put("loss_sell_line",loss_sell_line);
        hm.put("interest_rate",interest_rate);
        hm.put("interest",interest);
        hm.put("repare_capitical",repare_capitical);
        hm.put("leverage_name",leverageName);
        hm.put("use_time",capitial_used_time);
        return RetResponse.makeOKRsp(hm);
    }

    @RequestMapping(value = "/getHeYueLeverage",method = RequestMethod.GET)
    public RetResult getHeYueLeverage()
    {

        HashMap<String,Object> hm = new HashMap<>();
        List<Map> validHeYueIdList = HeYueImpl.findValidHeYueIdList();
        List<Map> validLeverageIdList = LeverageImpl.findValidLeverageIdList();
        Member member = memberMapper.findMember(MemberHolder.getUsername());
        hm.put("validHeYueIdList",validHeYueIdList);
        hm.put("validLeverageIdList",validLeverageIdList);
        hm.put("amount",member.getAmount());
        return RetResponse.makeOKRsp(hm);
    }

    @RequestMapping(value = "/applyHeYue",method = RequestMethod.GET)
    public RetResult applyHeYue(@RequestParam(value = "deposit") double deposit,@RequestParam(value = "heyue_id") int heyue_id, @RequestParam(value = "leverage_id") int leverage_id)
    {
        HashMap<String,Object> hm = new HashMap<>();
        double leverage_money = HeYueCaculateImpl.cal_leverage_money(deposit, leverage_id);
        double total_capitial = HeYueCaculateImpl.cal_total_capitial(deposit,leverage_money);
        double deposit_ = HeYueCaculateImpl.cal_deposit(deposit);
        double loss_warning_line = HeYueCaculateImpl.cal_loss_warning_line(deposit,0.5,leverage_money);
        double loss_sell_line = HeYueCaculateImpl.cal_loss_sell_line(deposit,0.2, leverage_money);
        double interest_rate = HeYueCaculateImpl.cal_interest_rate(heyue_id,leverage_id);
        int capitial_used_time = HeYueCaculateImpl.cal_capitial_used_time(heyue_id);
        double interest = HeYueCaculateImpl.cal_interest(interest_rate,leverage_money,capitial_used_time);
        double repare_capitical = HeYueCaculateImpl.cal_repare_capitical(deposit,interest);
        int member_id = MemberHolder.getId();
        hm.put("leverage_money",leverage_money);
        hm.put("member_id",member_id);
        hm.put("total_capital",total_capitial);
        hm.put("deposit",deposit_);
        hm.put("loss_warning_line",loss_warning_line);
        hm.put("loss_sell_line",loss_sell_line);
        hm.put("interest_rate",interest_rate);
        hm.put("interest",interest);
        hm.put("repare_capital",repare_capitical);
        hm.put("capital_used_time",capitial_used_time);
        hm.put("apply_state",0);
        try {
            int i = MemberHeYueApplyImpl.addOneHeYueApply(hm);
            if(i == 1){
                return RetResponse.makeOKRsp();
            }
        }catch (Exception e){
           e.printStackTrace();
        }
        return RetResponse.makeErRsp();
    }

    @RequestMapping(value = "/selectMemberHeYueByCase")

    public RetResult selectMemberHeYueByCase(){
        Map<Object, Object> map = new HashMap<>();
        map.put("member_id",MemberHolder.getId());
        List map1 = MemberServiceImpl.selectMemberHeYueByCase(map);
        return RetResponse.makeOKRsp(map1);
    }
}
