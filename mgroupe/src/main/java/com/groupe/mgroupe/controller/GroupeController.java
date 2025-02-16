package com.groupe.mgroupe.controller;

import com.groupe.mgroupe.dto.GroupeDTO;
import com.groupe.mgroupe.dto.SimpleGroupeDTO;
import com.groupe.mgroupe.exception.GroupeException;
import com.groupe.mgroupe.model.Groupe;
import com.groupe.mgroupe.service.GroupeService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.events.EventException;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("groupe")
@AllArgsConstructor
public class GroupeController {

    private GroupeService groupeService;
    @GetMapping(value = "/health/readiness")
    public ResponseEntity<String> test(){
        return ResponseEntity.status(HttpStatus.OK).body("hi");
    }
    @PostMapping
    public ResponseEntity<GroupeDTO> saveGroupe(@RequestBody GroupeDTO groupeDto){
        GroupeDTO savedGroupe = groupeService.saveGroupe(groupeDto);
        return new ResponseEntity<>(savedGroupe, HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllGroupe() {
        List<GroupeDTO> groupes = groupeService.getALLGroupe();
        return new ResponseEntity<>(groupes, groupes.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{idGroupe}")
    public GroupeDTO getGroupe(@PathVariable("idGroupe") Long idGroupe) throws GroupeException {
        try {
            return groupeService.getGroupe(idGroupe);
        } catch (GroupeException e) {
            return new GroupeDTO();
        }
    }

    @DeleteMapping("/{idGroupe}")
    public ResponseEntity<?> deleteById(@PathVariable("idGroupe") Long idGroupe) throws GroupeException{
        try{
            groupeService.deleteGroupe(idGroupe);
            return new ResponseEntity<>("Successfully deleted groupe with id "+idGroupe, HttpStatus.OK);
        }
        catch (GroupeException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idGroupe}")
    public ResponseEntity<?> updateById(@PathVariable("idGroupe") Long idGroupe, @RequestBody Groupe groupe)
    {
        try {
            groupeService.updateGroupe(idGroupe,groupe);
            return new ResponseEntity<>("Updated groupe with id "+idGroupe+"", HttpStatus.OK);
        }
        catch(ConstraintViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (GroupeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add_membre/{idGroupe}/{idUser}")
    public ResponseEntity<?> addMembre(@PathVariable("idGroupe") Long idGroupe,@PathVariable("idUser") Long idUser){
        try{
            groupeService.addMembre(idGroupe,idUser);
            return new ResponseEntity<>("Successfully added membre with id "+idUser+"in groupe:"+idGroupe, HttpStatus.OK);
        }
        catch (GroupeException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete_membre/{idGroupe}/{idUser}")
    public ResponseEntity<?> deleteByGroupeUser(@PathVariable("idGroupe") Long idGroupe,@PathVariable("idUser") Long idUser) throws GroupeException{
        try{
            groupeService.removeMembre(idGroupe,idUser);
            return new ResponseEntity<>("Successfully deleted membre with id "+idUser+"from groupe :"+idGroupe, HttpStatus.OK);
        }
        catch (GroupeException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/by_user/{idUser}")
    public ResponseEntity<?> getGroupeByUser(@PathVariable("idUser") Long idUser) {
        List<GroupeDTO> groupes = groupeService.getGroupeByUser(idUser);
        return new ResponseEntity<>(groupes, groupes.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    @GetMapping("/by_membre/{idUser}")
    public ResponseEntity<?> getGroupeByMembre(@PathVariable("idUser") Long idUser) {
        List<GroupeDTO> groupes = groupeService.getGroupeByMembre(idUser);
        return new ResponseEntity<>(groupes, groupes.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/leave_groupe/{idGroupe}/{idUser}")
    public ResponseEntity<?> leaveGroupe(@PathVariable("idGroupe") Long idGroupe,@PathVariable("idUser") Long idUser) throws GroupeException{
        try{
            groupeService.leaveGroupe(idGroupe,idUser);
            return new ResponseEntity<>("Successfully deleted membre with id "+idUser+"from groupe :"+idGroupe, HttpStatus.OK);
        }
        catch (GroupeException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/simple_groupe/{idGroupe}")
    public SimpleGroupeDTO getSimpleGroupe(@PathVariable("idGroupe") Long idGroupe) throws GroupeException {
        try {
            return groupeService.getSimpleGroupe(idGroupe);
        } catch (GroupeException e) {
            return new SimpleGroupeDTO();
        }
    }
}
