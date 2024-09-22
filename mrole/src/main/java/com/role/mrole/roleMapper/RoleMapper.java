package com.role.mrole.roleMapper;

import com.role.mrole.dto.RoleDTO;
import com.role.mrole.dto.UserDTO;
import com.role.mrole.model.Role;

import java.util.ArrayList;

public class RoleMapper {

    public static RoleDTO mapToRoleDto(Role role){
        RoleDTO roleDto = new RoleDTO(
                role.getIdRole(),
                role.getTitle(),
                role.getDescription(),
                role.getStatus(),
                role.getIdUser(),
                new UserDTO(),
                new ArrayList<>()

        );
        return roleDto;
    }

    public static Role mapToRole(RoleDTO roleDto){
        Role role = new Role(
                roleDto.getIdRole(),
                roleDto.getTitle(),
                roleDto.getDescription(),
                roleDto.getStatus(),
                roleDto.getIdUser()
        );
        return role;
    }
}
