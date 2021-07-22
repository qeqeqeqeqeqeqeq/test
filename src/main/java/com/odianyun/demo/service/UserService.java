package com.odianyun.demo.service;

import com.odianyun.demo.model.DTO.UserDTO;
import com.odianyun.demo.model.User;

import java.util.List;

public interface UserService {
    User getById(Long id);

    void update(UserDTO dto);

    void updateParam(UserDTO dto);

    void batchUpdate(List<UserDTO> dtoList);
}
