package com.odianyun.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.odianyun.demo.mapper.ListMapper;
import com.odianyun.demo.model.DTO.CodeDTO;
import com.odianyun.demo.model.VO.CodeVO;
import com.odianyun.demo.service.ListService;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class ListServiceImpl implements ListService {

    private static final String CONTRACT_CHANGE_USER_TYPE = "user";
    @Resource
    private RedisTemplate<String, Serializable> redisTemplate;
    @Resource
    private ListMapper listMapper;

    @Override
    public List<CodeVO> queryByPoolAndCategory(CodeDTO dtos) {
        ValueOperations<String, Serializable> operations = redisTemplate.opsForValue();
        String key = CONTRACT_CHANGE_USER_TYPE + dtos.getPool() + dtos.getCategory();
        List<CodeVO> cacheCode = (List<CodeVO>) operations.get(key);

        if (!CollectionUtils.isEmpty(cacheCode)) {
            Iterator<CodeVO> iterator = cacheCode.iterator();
            while (iterator.hasNext()) {
                System.out.println(JSONObject.toJSONString(iterator.next()));
            }
            return (List<CodeVO>) cacheCode;
        }
        List<CodeVO> code = listMapper.listByCategory(dtos);
//        operations.set(key, code);

        return code;
    }

    @Override
    public List<CodeVO> getNameByCode(CodeDTO dto) {
        ValueOperations operations = redisTemplate.opsForValue();

        String key = CONTRACT_CHANGE_USER_TYPE + dto.getPool() + dto.getCategory() + dto.getCode();
        System.out.println(key);
        List<CodeVO> cacheCode = (List<CodeVO>) operations.get(key);

        if (!CollectionUtils.isEmpty(cacheCode)) {
            Iterator<CodeVO> iterator = cacheCode.iterator();
            while (iterator.hasNext()) {
                System.out.println(JSONObject.toJSONString(iterator.next()));
            }
            return (List<CodeVO>) cacheCode;
        }
        List<CodeVO> Code = listMapper.getNameByCode(dto);
        operations.set(key, Code);
        return Code;
    }
}