package com.springbootmicroservices.userservice.service;


import com.springbootmicroservices.userservice.model.SimpleUserDTO;
import com.springbootmicroservices.userservice.model.UserDTO;
import com.springbootmicroservices.userservice.model.user.User;

import java.util.List;

public interface UserService {

    UserDTO saveUser(UserDTO userDTO);

    List<UserDTO> getALLUser();

    void deleteUser(Long idUser) ;

    User getUser(Long idUser) ;
    SimpleUserDTO getSimpleUser(Long idUser) ;

    User getUserByEmail(String email) ;
}
