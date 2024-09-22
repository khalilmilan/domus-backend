package com.mrole_permission.mrole_permission.service;

import com.mrole_permission.mrole_permission.dto.PermissionDTO;
import com.mrole_permission.mrole_permission.dto.RolePermissionDTO;
import com.mrole_permission.mrole_permission.exception.RolePermissionException;
import com.mrole_permission.mrole_permission.model.RolePermission;

import java.util.List;

public interface RolePermissionService {

    RolePermissionDTO saveRolePermission(RolePermissionDTO rolePermissionDto);

    List<RolePermissionDTO> getALLRolePermission();

    void deleteRolePermission(Long idRolePermission) throws RolePermissionException;

    RolePermissionDTO getRolePermission(Long idRolePermission) throws RolePermissionException;

    public void updateRolePermission(Long id, RolePermission rolePermission) throws RolePermissionException;

    public List<PermissionDTO> getRolePermissionByRole(Long idRole);
    public void deleteRolePermissionByIdRoleIdPermission(Long idRole,Long idPermission) throws RolePermissionException;
}
