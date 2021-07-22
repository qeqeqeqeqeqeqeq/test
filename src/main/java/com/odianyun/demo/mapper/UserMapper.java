package com.odianyun.demo.mapper;

import com.odianyun.demo.model.DTO.UserDTO;
import com.odianyun.demo.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface UserMapper {
    User getById(Long id);

    void update(UserDTO dto);

    void updateParam(String password, String mobile, Long id);

    void batchUpdate(List<UserDTO> dtoList);
}
