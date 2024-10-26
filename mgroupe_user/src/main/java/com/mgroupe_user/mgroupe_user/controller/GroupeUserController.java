package com.mgroupe_user.mgroupe_user.controller;

import com.mgroupe_user.mgroupe_user.dto.GroupeUserDTO;
import com.mgroupe_user.mgroupe_user.dto.SimpleUserDTO;
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
    public GroupeUserDTO saveGroupeUser(@RequestBody GroupeUserDTO groupeUserDto){
        GroupeUserDTO savedGroupeUser = groupeUserService.saveGroupeUser(groupeUserDto);
        return savedGroupeUser;
    }
    @GetMapping("")
    public ResponseEntity<?> getAllGroupesUser() {
        List<GroupeUserDTO> groupes = groupeUserService.getALLGroupeUser();
        return new ResponseEntity<>(groupes, groupes.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{idGroupeUser}")
    public GroupeUserDTO getGroupeUser(@PathVariable("idGroupeUser") Long idGroupeUser) throws GroupeUserException {
        try {
             return groupeUserService.getGroupeUser(idGroupeUser);
        } catch (GroupeUserException e) {
            return new GroupeUserDTO();
        }
    }

    @DeleteMapping("/{idGroupeUser}")
    public void deleteById(@PathVariable("idGroupeUser") Long idGroupeUser) throws GroupeUserException{
        try{
            groupeUserService.deleteGroupeUser(idGroupeUser);

        }
        catch (GroupeUserException e)
        {
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
    List<SimpleUserDTO> getAllUser(@PathVariable("idGroupe")Long idGroupe){
        List<SimpleUserDTO> membres = groupeUserService.getGroupeUserByGroupe(idGroupe);
        return membres;
    }
    @DeleteMapping("/delete_membre/{idGroupe}/{idUser}")
    public void deleteByGroupeAndUser(@PathVariable("idGroupe") Long idGroupe,@PathVariable("idUser") Long idUser) throws GroupeUserException{
        try{
            groupeUserService.deleteGroupeUserByIdGroupeIdUser(idGroupe,idUser);

        }
        catch (GroupeUserException e)
        {
        }
    }
    @GetMapping("/not_in/{idGroupe}")
    List<SimpleUserDTO> getAllPossibleUser(@PathVariable("idGroupe")Long idGroupe){
        List<SimpleUserDTO> membres = groupeUserService.getPossibleUser(idGroupe);
        return membres;
    }
    @GetMapping("/by_membre/{idUser}")
    List<Long> getGroupebyMembre(@PathVariable("idUser") Long idUser){
         return groupeUserService.findGroupeByMembre(idUser);
    }
}
