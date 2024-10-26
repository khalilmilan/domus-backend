package com.groupe.mgroupe.client;

import com.groupe.mgroupe.configurations.FeignClientSecurityConfig;
import com.groupe.mgroupe.dto.GroupeUserDTO;
import com.groupe.mgroupe.dto.SimpleUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="microservice-groupe-user",path = "/groupe_user",configuration = FeignClientSecurityConfig.class)
public interface GroupeUserFeignClient {

    @GetMapping(value = "/groupe/{idGroupe}",consumes = "application/json")
    List<SimpleUserDTO> getAllUser(@PathVariable("idGroupe")Long idGroupe);
    @PostMapping(value = "",consumes = "application/json")
    GroupeUserDTO saveGroupeUser(@RequestBody GroupeUserDTO groupeUserDto);
    @DeleteMapping(value="/delete_membre/{idGroupe}/{idUser}")
    void deleteByGroupeAndUser(@PathVariable("idGroupe") Long idGroupe,@PathVariable("idUser") Long idUser);

    @GetMapping(value = "/by_membre/{idUser}",consumes = "application/json")
    List<Long> getGroupebyMembre(@PathVariable("idUser")Long idUser);
}
