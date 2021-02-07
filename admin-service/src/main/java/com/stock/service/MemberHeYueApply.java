package com.stock.service;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

public interface MemberHeYueApply {

    /**
     * 申请一个新的合约
     *
     * @param map
     * @return
     */

    int addOneHeYueApply(Map map);
}
