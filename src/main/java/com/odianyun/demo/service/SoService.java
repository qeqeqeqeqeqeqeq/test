package com.odianyun.demo.service;

import com.odianyun.demo.model.DTO.SoDTO;
import com.odianyun.demo.model.ListResult;
import com.odianyun.demo.model.VO.SoVO;

import java.util.List;

public interface SoService {

    ListResult<SoVO> listSoPage(SoDTO soDTO);

    void batchUpdateStatus(List<String> dtoList);
}
