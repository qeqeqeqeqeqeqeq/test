package com.odianyun.demo.web;

import com.odianyun.demo.model.DTO.UserDTO;
import com.odianyun.demo.model.User;

import com.odianyun.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("getById")
    public User getById(Long id){
        return userService.getById(id);
    }

    @PostMapping("update")
    public User update(@RequestBody UserDTO dto){
        userService.update(dto);
        return userService.getById(dto.getId());
    }

    @PostMapping("updateParam")
    public User updateParam(@RequestBody UserDTO dto){
        userService.updateParam(dto);
        return userService.getById(dto.getId());
    }

    @PostMapping("batchUpdate")
    public void batchUpdate(@RequestBody List<UserDTO> dtoList){
        userService.batchUpdate(dtoList);
    }


}

