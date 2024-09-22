package com.permission.mpermission.controller;

import com.permission.mpermission.dto.PermissionDTO;
import com.permission.mpermission.dto.UserDTO;
import com.permission.mpermission.exception.PermissionException;
import com.permission.mpermission.model.Permission;
import com.permission.mpermission.service.PermissionService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("permission")
@AllArgsConstructor
public class PermissionController {
    private PermissionService permissionService;
    @GetMapping(value = "/lowel")
    public String test(){
        return "hi";
    }
    @PostMapping
    public ResponseEntity<PermissionDTO> saveEvent(@RequestBody PermissionDTO permissionDto){
        PermissionDTO savedPermission = permissionService.savePermission(permissionDto);
        return new ResponseEntity<>(savedPermission, HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllPermission() {
        List<PermissionDTO> permissions = permissionService.getALLPermission();
        return new ResponseEntity<>(permissions, permissions.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{idPermission}")
    public ResponseEntity<PermissionDTO> getPermission(@PathVariable("idPermission") Long idPermission) throws PermissionException {
        try {
            return new ResponseEntity<>(permissionService.getPermission(idPermission), HttpStatus.OK);
        } catch (PermissionException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PermissionDTO());
        }
    }

    @DeleteMapping("/{idPermission}")
    public ResponseEntity<?> deleteById(@PathVariable("idPermission") Long idPermission) throws PermissionException{
        try{
            permissionService.deletePermission(idPermission);
            return new ResponseEntity<>("Successfully deleted permission with id "+idPermission, HttpStatus.OK);
        }
        catch (PermissionException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idPermission}")
    public ResponseEntity<?> updateById(@PathVariable("idPermission") Long idPermission, @RequestBody Permission permission)
    {
        try {
            permissionService.updatePermission(idPermission,permission);
            return new ResponseEntity<>("Updated permission with id "+idPermission+"", HttpStatus.OK);
        }
        catch(ConstraintViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (PermissionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
