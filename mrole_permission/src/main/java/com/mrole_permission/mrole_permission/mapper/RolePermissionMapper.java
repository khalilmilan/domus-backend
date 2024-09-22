package com.mrole_permission.mrole_permission.mapper;

import com.mrole_permission.mrole_permission.dto.RolePermissionDTO;
import com.mrole_permission.mrole_permission.model.RolePermission;

public class RolePermissionMapper {

    public static RolePermissionDTO mapToRolePermissionDTO(RolePermission rolePermission){
        RolePermissionDTO rolePermissionDto = new RolePermissionDTO(
                rolePermission.getIdRolePermission(),
                rolePermission.getIdRole(),
                rolePermission.getIdPermission(),
                rolePermission.getStatus()
        );
        return rolePermissionDto;
    }

    public static RolePermission mapToRolePermission(RolePermissionDTO groupeUserDto){
        RolePermission rolePermission = new RolePermission(
                groupeUserDto.getIdRolePermission(),
                groupeUserDto.getIdRole(),
                groupeUserDto.getIdPermission(),
                groupeUserDto.getStatus()
        );
        return rolePermission;
    }
}
