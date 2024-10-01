package com.springbootmicroservices.userservice.service.impl;

import com.springbootmicroservices.userservice.model.RoleDTO;
import com.springbootmicroservices.userservice.model.SimpleUserDTO;
import com.springbootmicroservices.userservice.model.UserDTO;
import com.springbootmicroservices.userservice.model.user.User;
import com.springbootmicroservices.userservice.model.user.entity.UserEntity;
import com.springbootmicroservices.userservice.model.user.mapper.UserEntityToUserMapper;
import com.springbootmicroservices.userservice.model.user.mapper.UserMapper;
import com.springbootmicroservices.userservice.repository.UserRepository;
import com.springbootmicroservices.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private final UserEntityToUserMapper userEntityToUserMapper = UserEntityToUserMapper.initialize();
    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public List<UserDTO> getALLUser() {
        return null;
    }

    @Override
    public void deleteUser(Long idUser) {

    }

    @Override
    public User getUser(Long idUser) {
        Optional<UserEntity> userOptional = userRepository.findById(idUser);
        if (userOptional ==null) {
            return null;
        }else {
            return userEntityToUserMapper.map(userOptional.get());
        }
    }

    @Override
    public SimpleUserDTO getSimpleUser(Long idUser) {
        Optional<UserEntity> userOptional = userRepository.findById(idUser);
        if (userOptional ==null) {
            return null;
        }else {
            return UserMapper.mapToSimpleDTO(userOptional.get());
        }
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<UserEntity> userOptional = userRepository.findUserEntityByEmail(email);
        if (userOptional ==null) {
           return null;
        }else {

            return userEntityToUserMapper.map(userOptional.get());
        }
    }
}
