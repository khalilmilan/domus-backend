package com.muser.muser.service;

import com.muser.muser.dto.UserDTO;
import com.muser.muser.exception.UserException;
import com.muser.muser.model.User;

import java.util.List;

public interface UserService {

    UserDTO saveUser(UserDTO userDTO);

    List<UserDTO> getALLUser();

    void deleteUser(Long idUser) throws UserException;

    UserDTO getUser(Long idUser) throws UserException;

    public void updateUser(Long id, User user) throws UserException;
}
