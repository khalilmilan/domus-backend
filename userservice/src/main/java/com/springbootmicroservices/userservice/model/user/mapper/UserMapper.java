package com.springbootmicroservices.userservice.model.user.mapper;

import com.springbootmicroservices.userservice.model.SimpleUserDTO;
import com.springbootmicroservices.userservice.model.UserDTO;
import com.springbootmicroservices.userservice.model.user.User;
import com.springbootmicroservices.userservice.model.user.entity.UserEntity;

import java.util.ArrayList;

public class UserMapper {
    public static SimpleUserDTO mapToSimpleDTO(UserEntity user){
        SimpleUserDTO simpleUserDto = new SimpleUserDTO(
                user.getIdUser(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoto()
        );
        return simpleUserDto;
    }
}
