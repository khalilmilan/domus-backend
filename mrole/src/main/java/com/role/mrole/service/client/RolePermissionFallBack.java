package com.role.mrole.service.client;

import com.role.mrole.dto.PermissionDTO;
import com.role.mrole.dto.RolePermissionDTO;
import org.springframework.http.ResponseEntity;
import java.util.List;


public class RolePermissionFallBack implements RolePermissionFeignClient{

    @Override
    public ResponseEntity<List<PermissionDTO>> getPermissionByRole(Long idRole) {
        return null;
    }

    @Override
    public ResponseEntity<RolePermissionDTO> saveRolePermission(RolePermissionDTO rolePermissionDto) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteByGroupeAndUser(Long idRole, Long idPermission) {
        return null;
    }
}
