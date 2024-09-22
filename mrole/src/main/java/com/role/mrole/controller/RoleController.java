package com.role.mrole.controller;

import com.role.mrole.dto.RoleDTO;
import com.role.mrole.exception.RoleException;
import com.role.mrole.model.Role;
import com.role.mrole.service.RoleService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("role")
@AllArgsConstructor
public class RoleController {
    private RoleService roleService;
    @GetMapping(value = "/lowel")
    public String test(){
        return "hi";
    }
    @PostMapping
    public ResponseEntity<RoleDTO> saveRole(@RequestBody RoleDTO RoleDTO){
        RoleDTO savedRole = roleService.saveRole(RoleDTO);
        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
    }
    @GetMapping("/all_role")
    public ResponseEntity<?> getAllRole() {
        List<RoleDTO> roles = roleService.getALLRole();
        return new ResponseEntity<>(roles, roles.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{idRole}")
    public ResponseEntity<?> getRole(@PathVariable("idRole") Long idRole) throws RoleException {
        try {
            return new ResponseEntity<>(roleService.getRole(idRole), HttpStatus.OK);
        } catch (RoleException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idRole}")
    public ResponseEntity<?> deleteById(@PathVariable("idRole") Long idRole) throws RoleException{
        try{
            roleService.deleteRole(idRole);
            return new ResponseEntity<>("Successfully deleted role with id "+idRole, HttpStatus.OK);
        }
        catch (RoleException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idRole}")
    public ResponseEntity<?> updateById(@PathVariable("idRole") Long idRole, @RequestBody Role role)
    {
        try {
            roleService.updateRole(idRole,role);
            return new ResponseEntity<>("Updated role with id "+idRole+"", HttpStatus.OK);
        }
        catch(ConstraintViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (RoleException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/add_permission/{idRole}/{idPermission}")
    public ResponseEntity<?> addMembre(@PathVariable("idRole") Long idRole,@PathVariable("idPermission") Long idPermission){

        try{
            roleService.addPermission(idRole,idPermission);
            return new ResponseEntity<>("Successfully added permission with id "+idPermission+"in role:"+idRole, HttpStatus.OK);
        }
        catch (RoleException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete_permission/{idRole}/{idPermission}")
    public ResponseEntity<?> deleteByRoleAndPermission(@PathVariable("idRole") Long idRole,@PathVariable("idPermission") Long idPermission) throws RoleException{
        try{
            roleService.removePermission(idRole,idPermission);
            return new ResponseEntity<>("Successfully deleted rolePermission with idGroupe "+idRole+" and idPermission:"+idPermission, HttpStatus.OK);
        }
        catch (RoleException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
