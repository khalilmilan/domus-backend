package com.muser.muser.service.client;

import com.muser.muser.dto.RoleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="microservice-role", fallback = RoleFallback.class)
public interface RoleFeignClient {
    @GetMapping(value="/role/{idRole}",consumes = "application/json")
    ResponseEntity<RoleDTO> getRole(@PathVariable("idRole") Long idRole);
}
