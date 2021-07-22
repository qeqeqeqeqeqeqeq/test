package com.odianyun.demo.mapper;

import com.odianyun.demo.model.DTO.CodeDTO;
import com.odianyun.demo.model.VO.CodeVO;

import java.util.List;
import java.util.Map;

public interface ListMapper {


    List<CodeVO> listByCategory(CodeDTO dtos);

    List<CodeVO> getNameByCode(CodeDTO dto);
}
