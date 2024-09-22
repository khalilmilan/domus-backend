package com.mrole_permission.mrole_permission.service.client;

import com.mrole_permission.mrole_permission.dto.PermissionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="microservice-permission", fallback = PermissionFallback.class)
public interface PermissionFeignClient {
    @GetMapping(value = "/permission/{idPermission}")
    public ResponseEntity<PermissionDTO> getPermission(@PathVariable("idPermission") Long idPermission);
}
