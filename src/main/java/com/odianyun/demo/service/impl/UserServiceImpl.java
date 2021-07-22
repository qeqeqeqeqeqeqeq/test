package com.odianyun.demo.service.impl;

import com.odianyun.demo.mapper.UserMapper;
import com.odianyun.demo.model.DTO.UserDTO;
import com.odianyun.demo.model.User;
import com.odianyun.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getById(Long id) {
        return userMapper.getById(id);
    }

    @Override
    public void update(UserDTO dto) {
        userMapper.update(dto);
    }

    @Override
    public void updateParam(@RequestBody UserDTO dto){
        String password = dto.getPassword();
        String mobile = dto.getMobile();
        Long id = dto.getId();
        userMapper.updateParam(password,mobile,id);
    }

    @Override
    public void batchUpdate(List<UserDTO> dtoList) {
       userMapper.batchUpdate(dtoList);
    }
}

/*    @Resource

    @Override
    public List<SoVO> list(SoDTO dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
//        PageHelper.offsetPage(dto.getPageNum(), dto.getPageSize());
//        PageHelper.startPage(dto.getPageNum(), dto.getPageSize(), false);
        return soMapper.list(dto);
    }
    @Override
    public SoVO get(SoDTO dto) {
        return soMapper.get(dto);
    }
    @Override
    public List<SoVO> choose(SoDTO dto) {
        return soMapper.choose(dto);
    }
    @Override
    public ListResult<SoVO> listPage(SoDTO dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<SoVO> list = soMapper.list(dto);
        PageInfo page = new PageInfo(list);
        *//*ListResult<SoVO> result = new ListResult<SoVO>();
        result.setPages(page.getPages());
        result.setTotal(page.getTotal());
        result.setList(page.getList());
        return result;*//*

        return new ListResult<SoVO>(page.getTotal(), page.getPages(), page.getList());
    }

    @Override
    public ListResult<SoVO> listSoPage(SoDTO dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<SoVO> list = soMapper.listSoPage(dto);
        PageInfo page = new PageInfo(list);
        return new ListResult<SoVO>(page.getTotal(), page.getPages(), page.getList());
    }

    @Override
    public void batchUpdateStatus(List<String> dtoList) {
        List<SoVO> list = soMapper.listByOrderCodes(dtoList);
        list.stream().filter(item -> SoConstant.ORDER_STATUS_DELIVERED.compareTo(item.getOrderStatus()) > 0)
                .forEach(item -> item.setOrderStatus(SoConstant.ORDER_STATUS_DELIVERED));
//        List<SoVO> list2 = Lists.newArrayListWithCapacity(list.size());

        *//*List<SoVO> list2 = Lists.newArrayList();
        for(SoVO soVO : list){
            if (SoConstant.ORDER_STATUS_DELIVERED.compareTo(soVO.getOrderStatus()) > 0) {
                soVO.setOrderStatus(SoConstant.ORDER_STATUS_DELIVERED);
                list2.add(soVO);
            }
        }*//*

        soMapper.batchUpdateStatus(list);
    }
}*/
