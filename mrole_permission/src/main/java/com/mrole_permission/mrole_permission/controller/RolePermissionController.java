package com.mrole_permission.mrole_permission.controller;

import com.mrole_permission.mrole_permission.dto.PermissionDTO;
import com.mrole_permission.mrole_permission.dto.RolePermissionDTO;
import com.mrole_permission.mrole_permission.exception.RolePermissionException;
import com.mrole_permission.mrole_permission.model.RolePermission;
import com.mrole_permission.mrole_permission.service.RolePermissionService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("role_permission")
@AllArgsConstructor
public class RolePermissionController {
    private RolePermissionService rolePermissionService;
    @GetMapping(value = "/lowel")
    public String test(){
        return "hi";
    }
    @PostMapping
    public ResponseEntity<RolePermissionDTO> saveRolePermission(@RequestBody RolePermissionDTO rolePermissionDto){
        RolePermissionDTO savedGroupeUser = rolePermissionService.saveRolePermission(rolePermissionDto);
        return new ResponseEntity<>(savedGroupeUser, HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllRolePermission() {
        List<RolePermissionDTO> rolePermissions = rolePermissionService.getALLRolePermission();
        return new ResponseEntity<>(rolePermissions, rolePermissions.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{idRolePermission}")
    public ResponseEntity<?> getRolePermission(@PathVariable("idRolePermission") Long idRolePermission) throws RolePermissionException {
        try {
            return new ResponseEntity<>(rolePermissionService.getRolePermission(idRolePermission), HttpStatus.OK);
        } catch (RolePermissionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idRolePermission}")
    public ResponseEntity<?> deleteById(@PathVariable("idRolePermission") Long idRolePermission) throws RolePermissionException{
        try{
            rolePermissionService.deleteRolePermission(idRolePermission);
            return new ResponseEntity<>("Successfully deleted role permission with id "+idRolePermission, HttpStatus.OK);
        }
        catch (RolePermissionException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idRolePermission}")
    public ResponseEntity<?> updateById(@PathVariable("idRolePermission") Long idRolePermission, @RequestBody RolePermission rolePermission)
    {
        try {
            rolePermissionService.updateRolePermission(idRolePermission,rolePermission);
            return new ResponseEntity<>("Updated groupeUser with id "+idRolePermission+"", HttpStatus.OK);
        }
        catch(ConstraintViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (RolePermissionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

      @GetMapping("/role/{idRole}")
        ResponseEntity<List<PermissionDTO>> getPermissionByRole(@PathVariable("idRole")Long idRole){
        List<PermissionDTO> permissions = rolePermissionService.getRolePermissionByRole(idRole);
        return new ResponseEntity<>(permissions, permissions.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete_permission/{idRole}/{idPermission}")
    public ResponseEntity<String> deleteByGroupeAndUser(@PathVariable("idRole") Long idRole,@PathVariable("idPermission") Long idPermission) throws RolePermissionException{
        try{
            rolePermissionService.deleteRolePermissionByIdRoleIdPermission(idRole,idPermission);
            return new ResponseEntity<>("Successfully deleted rolePermission with idRole "+idRole+" and idPermission:"+idPermission, HttpStatus.OK);
        }
        catch (RolePermissionException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
