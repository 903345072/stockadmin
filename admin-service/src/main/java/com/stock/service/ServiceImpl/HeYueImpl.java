package com.stock.service.ServiceImpl;


import com.alibaba.fastjson.JSON;
import com.mapper.HeYueMapper;
import com.stock.models.HeYue;
import com.stock.service.HeYueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HeYueImpl implements HeYueService {
    @Autowired
    HeYueMapper heYueMapper;
    @Override
    public List findHeYue(Map map) {
        return heYueMapper.findHeYueByPage(map);
    }

    @Override
    public int findHeYueCount(Map map) {
        return heYueMapper.findHeYueCount(map);
    }

    @Override
    public int insertOneHeYue(HeYue heYu) {
        return heYueMapper.insertOneHeYue(heYu);
    }

    @Override
    public int updateOneHeYue(HeYue heY) {
        return heYueMapper.updateOneHeYue(heY);
    }

    @Override
    public int updateHeYueStaus(Map map) {
        return heYueMapper.updateHeYueStaus(map);
    }

    @Override
    public Map findHeYueNameById(Integer id) {
        return heYueMapper.findHeYueNameById(id);
    }

    @Override
    public List<Map> findValidHeYueIdList() {
        return heYueMapper.findValidHeYueIdList();
    }
}
