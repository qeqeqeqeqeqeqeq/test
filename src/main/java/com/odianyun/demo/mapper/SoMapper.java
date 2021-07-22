package com.odianyun.demo.mapper;

import com.odianyun.demo.model.DTO.SoDTO;
import com.odianyun.demo.model.VO.SoVO;

import java.util.List;

public interface SoMapper {

    List<SoVO> listSoPage(SoDTO soDTO);

    void batchUpdateStatus(List<SoVO> list);

    List<SoVO> listByOrderCodes(List<String> dtoList);
}
