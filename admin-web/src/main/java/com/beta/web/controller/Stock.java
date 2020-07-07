package com.beta.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.show.api.ShowApiRequest;
import com.util.RetResponse;
import com.util.RetResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/stock")
public class Stock {
    @Value("${wwyy.appid}")     private String appid;
    @Value("${wwyy.appSecret}")     private  String appSecret;
    @RequestMapping("/getTimeSharingData/{stockCode}")
    public RetResult get(@PathParam(value = "stockCode") String stockCode) {
        String res=new ShowApiRequest("http://route.showapi.com/131-49",appid,appSecret)
                .addTextPara("code","002008")
                .addTextPara("day","1")
                .post();
        HashMap<String,Object> hm = new HashMap<String,Object>();
        hm.put("data",res);
        return RetResponse.makeOKRsp(hm);
    }

    @RequestMapping("/getKdata/{stockCode}")
    public RetResult getKdata(@PathVariable String stockCode) {
        String res=new ShowApiRequest("http://route.showapi.com/131-50",appid,appSecret)
                .addTextPara("code","002008")
                .addTextPara("time",stockCode)
                .addTextPara("beginDay","20180801")
                .addTextPara("type","bfq")
                .post();
        HashMap<String,Object> hm = new HashMap<String,Object>();
        hm.put("data",res);
        return RetResponse.makeOKRsp(hm);
    }
}
