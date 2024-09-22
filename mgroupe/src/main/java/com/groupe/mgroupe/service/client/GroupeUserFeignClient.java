package com.groupe.mgroupe.service.client;

import com.groupe.mgroupe.dto.GroupeUserDTO;
import com.groupe.mgroupe.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="microservice-groupe-user", fallback = GroupeUserFallBack.class)
public interface GroupeUserFeignClient {

    @GetMapping(value = "/groupe_user/groupe/{idGroupe}",consumes = "application/json")
    public ResponseEntity<List<UserDTO>> getAllUser(@PathVariable("idGroupe")Long idGroupe);

    @PostMapping(value = "/groupe_user",consumes = "application/json")
    public ResponseEntity<GroupeUserDTO> saveGroupeUser(@RequestBody GroupeUserDTO groupeUserDto);


    @DeleteMapping(value="/groupe_user/delete_membre/{idGroupe}/{idUser}", consumes = "application/json")
    public ResponseEntity<?> deleteByGroupeAndUser(@PathVariable("idGroupe") Long idGroupe, @PathVariable("idUser") Long idUser);
}
