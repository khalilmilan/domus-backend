package com.mrole_permission.mrole_permission.service.client;

import com.mrole_permission.mrole_permission.dto.PermissionDTO;
import org.springframework.http.ResponseEntity;

public class PermissionFallback implements PermissionFeignClient{
    @Override
    public ResponseEntity<PermissionDTO> getPermission(Long idPermission) {
        return null;
    }
}
