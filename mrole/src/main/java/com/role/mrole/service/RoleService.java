package com.role.mrole.service;

import com.role.mrole.dto.RoleDTO;
import com.role.mrole.exception.RoleException;
import com.role.mrole.model.Role;

import java.util.List;

public interface RoleService {

    RoleDTO saveRole(RoleDTO roleDto);

    List<RoleDTO> getALLRole();

    void deleteRole(Long idRole) throws RoleException;

    RoleDTO getRole(Long idRole) throws RoleException;

     void updateRole(Long id, Role role) throws RoleException;

    void addPermission(Long idRole, Long idPermission) throws RoleException;

    void removePermission(Long idRole, Long idPermission) throws RoleException;

}
