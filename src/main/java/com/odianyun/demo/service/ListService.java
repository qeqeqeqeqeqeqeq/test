package com.odianyun.demo.service;

import com.odianyun.demo.model.DTO.CodeDTO;
import com.odianyun.demo.model.VO.CodeVO;

import java.util.List;
import java.util.Map;

public interface ListService {

    List<CodeVO> queryByPoolAndCategory(CodeDTO dtos);

    List<CodeVO> getNameByCode(CodeDTO dto);
}
