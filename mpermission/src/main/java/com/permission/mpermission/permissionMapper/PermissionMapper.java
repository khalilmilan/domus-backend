package com.permission.mpermission.permissionMapper;

import com.permission.mpermission.dto.PermissionDTO;
import com.permission.mpermission.dto.UserDTO;
import com.permission.mpermission.model.Permission;

public class PermissionMapper {

    public static PermissionDTO mapToPermissionDto(Permission permission){
        PermissionDTO permissionDto = new PermissionDTO(
                permission.getIdPermission(),
                permission.getTitle(),
                permission.getDescription(),
                permission.getStatus(),
                permission.getIdUser(),
                new UserDTO()

        );
        return permissionDto;
    }

    public static Permission mapToPermission(PermissionDTO permissionDto){
        Permission permission = new Permission(
                permissionDto.getIdPermission(),
                permissionDto.getTitle(),
                permissionDto.getDescription(),
                permissionDto.getStatus(),
                permissionDto.getIdUser()
        );
        return permission;
    }
}
