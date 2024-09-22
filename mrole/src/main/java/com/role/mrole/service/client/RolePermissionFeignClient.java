package com.role.mrole.service.client;

import com.role.mrole.dto.PermissionDTO;
import com.role.mrole.dto.RolePermissionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@FeignClient(name="microservice-role-permission", fallback = RolePermissionFallBack.class)
public interface RolePermissionFeignClient {

    @GetMapping(value="/role_permission/role/{idRole}",consumes = "application/jso")
    public ResponseEntity<List<PermissionDTO>> getPermissionByRole(@PathVariable("idRole") Long idRole);
    @PostMapping(value = "/role_permission",consumes = "application/json")
    public ResponseEntity<RolePermissionDTO> saveRolePermission(@RequestBody RolePermissionDTO rolePermissionDto);

    @DeleteMapping("/role_permission/delete_permission/{idRole}/{idPermission}")
    public ResponseEntity<String> deleteByGroupeAndUser(@PathVariable("idRole") Long idRole,@PathVariable("idPermission") Long idPermission);
}
