package com.role.mrole.client;

import com.role.mrole.configurations.FeignClientSecurityConfig;
import com.role.mrole.dto.PermissionDTO;
import com.role.mrole.dto.RolePermissionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="microservice-role-permission", path="/role_permission",configuration = FeignClientSecurityConfig.class)
public interface RolePermissionFeignClient {

    @GetMapping(value="/role/{idRole}",consumes = "application/json")
     List<PermissionDTO> getPermissionByRole(@PathVariable("idRole") Long idRole);
    @PostMapping(value = "",consumes = "application/json")
     RolePermissionDTO saveRolePermission(@RequestBody RolePermissionDTO rolePermissionDto);

    @DeleteMapping("/delete_permission/{idRole}/{idPermission}")
     void  deleteByGroupeAndUser(@PathVariable("idRole") Long idRole,@PathVariable("idPermission") Long idPermission);
}
