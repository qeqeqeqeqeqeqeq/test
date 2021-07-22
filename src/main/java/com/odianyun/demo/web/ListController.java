package com.odianyun.demo.web;

import com.odianyun.demo.model.Code;
import com.odianyun.demo.model.DTO.CodeDTO;
import com.odianyun.demo.model.VO.CodeVO;
import com.odianyun.demo.service.ListService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("list")
public class ListController {
    @Resource
    private ListService listService;

    @RequestMapping("listByCategory")
    List<CodeVO> listByCategory(@RequestBody CodeDTO dtos){

        return listService.queryByPoolAndCategory(dtos);
    }
    @RequestMapping ("getNameByCode")
    public List<CodeVO> getNameByCode(@RequestBody CodeDTO dto){
        return listService.getNameByCode(dto);
    }
}


