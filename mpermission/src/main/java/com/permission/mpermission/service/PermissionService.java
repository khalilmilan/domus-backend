package com.permission.mpermission.service;

import com.permission.mpermission.dto.PermissionDTO;
import com.permission.mpermission.exception.PermissionException;
import com.permission.mpermission.model.Permission;

import java.util.List;

public interface PermissionService {

    PermissionDTO savePermission(PermissionDTO permissionDto);

    List<PermissionDTO> getALLPermission();

    void deletePermission(Long idPermission) throws PermissionException;

    PermissionDTO getPermission(Long idPermission) throws PermissionException;

    public void updatePermission(Long idPermission, Permission permission) throws PermissionException;
}
