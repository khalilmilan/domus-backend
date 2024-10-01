package com.muser.muser.userMapper;

import com.muser.muser.dto.RoleDTO;
import com.muser.muser.dto.SimpleUserDTO;
import com.muser.muser.dto.UserDTO;
import com.muser.muser.model.User;

public class UserMapper {

    public static UserDTO mapToUserDto(User user){
        UserDTO userDto = new UserDTO(
                user.getIdUser(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoto(),
                user.getPassword(),
                user.getEmail(),
                user.getAdresse(),
                user.getGender(),
                user.getIdGroupe(),
                user.getBirthDate(),
                user.getPhoneNumber(),
                user.getStatus(),
                user.getUserType(),
                user.getIdRank(),
                user.getIdRole(),
                new RoleDTO()
        );
        return userDto;
    }
    public static SimpleUserDTO mapToSimpleUserDto(User user){
        SimpleUserDTO userDto = new SimpleUserDTO(
                user.getIdUser(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoto()
        );
        return userDto;

    }

    public static User mapToUser(UserDTO userDto){
        User user = new User(
                userDto.getIdUser(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getPhoto(),
                userDto.getPassword(),
                userDto.getEmail(),
                userDto.getAdresse(),
                userDto.getGender(),
                userDto.getIdGroupe(),
                userDto.getBirthDate(),
                userDto.getPhoneNumber(),
                userDto.getStatus(),
                userDto.getUserType(),
                userDto.getIdRole(),
                userDto.getIdRank()
        );
        return user;
    }
}
