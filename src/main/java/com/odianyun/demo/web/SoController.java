package com.odianyun.demo.web;

import com.odianyun.demo.model.DTO.SoDTO;
import com.odianyun.demo.model.ListResult;
import com.odianyun.demo.model.VO.SoVO;
import com.odianyun.demo.service.SoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("so")
public class SoController {
    @Resource
    private SoService soService;

    @RequestMapping("listSoPage")
    public ListResult<SoVO> listSoPage(@RequestBody SoDTO soDTO){
        ListResult<SoVO> list=soService.listSoPage(soDTO);
        return list;
    }

    @RequestMapping("batchUpdate")
    public String batchUpdateStatus(@RequestBody List<String> dtoList){
        soService.batchUpdateStatus(dtoList);
        return "success";
    }
}
