package com.mrole_permission.mrole_permission.client;

import com.mrole_permission.mrole_permission.configurations.FeignClientSecurityConfig;
import com.mrole_permission.mrole_permission.dto.PermissionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="microservice-permission", path = "/permission",configuration = FeignClientSecurityConfig.class)
public interface PermissionFeignClient {
    @GetMapping(value = "/{idPermission}")
   PermissionDTO getPermission(@PathVariable("idPermission") Long idPermission);
}
