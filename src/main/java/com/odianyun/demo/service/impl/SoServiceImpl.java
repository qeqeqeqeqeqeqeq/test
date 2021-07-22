package com.odianyun.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.odianyun.demo.constant.SoConstant;
import com.odianyun.demo.mapper.SoMapper;
import com.odianyun.demo.model.DTO.SoDTO;
import com.odianyun.demo.model.ListResult;
import com.odianyun.demo.model.VO.SoVO;
import com.odianyun.demo.service.SoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class SoServiceImpl implements SoService {
    @Resource
    private SoMapper soMapper;

    @Override
    public ListResult<SoVO> listSoPage(SoDTO soDTO) {
        PageHelper.startPage(soDTO.getPageNum(),soDTO.getPageSize());
        List<SoVO> list = soMapper.listSoPage(soDTO);
        PageInfo pageInfo=new PageInfo(list);
        return new ListResult<SoVO>(pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());
    }

    @Override
    public void batchUpdateStatus(List<String> dtoList) {
        List<SoVO> list=soMapper.listByOrderCodes(dtoList);/*返回id，order_code,order_status集合*/
        list.stream().filter(item -> SoConstant.ORDER_STATUS_DELIVERED.compareTo(item.getOrderStatus())>0)/*筛选小于1060的订单并遍历*/
                .forEach(item -> item.setOrderStatus(SoConstant.ORDER_STATUS_DELIVERED));
        soMapper.batchUpdateStatus(list);/*将小于1060的订单进行批量更新*/
    }
}
