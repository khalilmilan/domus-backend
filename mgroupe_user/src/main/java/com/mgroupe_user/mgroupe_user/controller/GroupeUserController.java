package com.mgroupe_user.mgroupe_user.controller;

import com.mgroupe_user.mgroupe_user.dto.GroupeUserDTO;
import com.mgroupe_user.mgroupe_user.dto.UserDTO;
import com.mgroupe_user.mgroupe_user.exception.GroupeUserException;
import com.mgroupe_user.mgroupe_user.model.GroupeUser;
import com.mgroupe_user.mgroupe_user.service.GroupeUserService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("groupe_user")
@AllArgsConstructor
public class GroupeUserController {

    private GroupeUserService groupeUserService;
    @GetMapping(value = "/lowel")
    public String test(){
        return "hi";
    }
    @PostMapping
    public ResponseEntity<GroupeUserDTO> saveGroupeUser(@RequestBody GroupeUserDTO groupeUserDto){
        GroupeUserDTO savedGroupeUser = groupeUserService.saveGroupeUser(groupeUserDto);
        return new ResponseEntity<>(savedGroupeUser, HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllGroupesUser() {
        List<GroupeUserDTO> groupes = groupeUserService.getALLGroupeUser();
        return new ResponseEntity<>(groupes, groupes.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{idGroupeUser}")
    public ResponseEntity<?> getGroupeUser(@PathVariable("idGroupeUser") Long idGroupeUser) throws GroupeUserException {
        try {
            return new ResponseEntity<>(groupeUserService.getGroupeUser(idGroupeUser), HttpStatus.OK);
        } catch (GroupeUserException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idGroupeUser}")
    public ResponseEntity<?> deleteById(@PathVariable("idGroupeUser") Long idGroupeUser) throws GroupeUserException{
        try{
            groupeUserService.deleteGroupeUser(idGroupeUser);
            return new ResponseEntity<>("Successfully deleted groupe with id "+idGroupeUser, HttpStatus.OK);
        }
        catch (GroupeUserException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idGroupeUser}")
    public ResponseEntity<?> updateById(@PathVariable("idGroupeUser") Long idGroupeUser, @RequestBody GroupeUser groupeUser)
    {
        try {
            groupeUserService.updateGroupeUser(idGroupeUser,groupeUser);
            return new ResponseEntity<>("Updated groupeUser with id "+idGroupeUser+"", HttpStatus.OK);
        }
        catch(ConstraintViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (GroupeUserException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/groupe/{idGroupe}")
    ResponseEntity<List<UserDTO>> getAllUser(@PathVariable("idGroupe")Long idGroupe){
        List<UserDTO> membres = groupeUserService.getGroupeUserByGroupe(idGroupe);
        return new ResponseEntity<>(membres, membres.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/delete_membre/{idGroupe}/{idUser}")
    public ResponseEntity<?> deleteByGroupeAndUser(@PathVariable("idGroupe") Long idGroupe,@PathVariable("idUser") Long idUser) throws GroupeUserException{
        try{
            groupeUserService.deleteGroupeUserByIdGroupeIdUser(idGroupe,idUser);
            return new ResponseEntity<>("Successfully deleted groupeUser with idGroupe "+idGroupe+" and iduser:"+idUser, HttpStatus.OK);
        }
        catch (GroupeUserException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
