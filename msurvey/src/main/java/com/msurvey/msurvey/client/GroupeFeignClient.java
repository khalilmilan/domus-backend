package com.msurvey.msurvey.client;

import com.msurvey.msurvey.configurations.FeignClientSecurityConfig;
import com.msurvey.msurvey.dto.GroupeDTO;
import com.msurvey.msurvey.dto.SimpleGroupeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(name="microservice-groupe", path = "/groupe",configuration = FeignClientSecurityConfig.class)

public interface GroupeFeignClient {
    @GetMapping(value = "/{idGroupe}",consumes = "application/json")
    GroupeDTO getGroupe(@PathVariable("idGroupe") Long idGroupe);

    @GetMapping(value = "/simple_groupe/{idGroupe}",consumes = "application/json")
    SimpleGroupeDTO getSimpleGroupe(@PathVariable("idGroupe") Long idGroupe);
}
