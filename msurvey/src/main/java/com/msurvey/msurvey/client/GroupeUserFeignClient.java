package com.msurvey.msurvey.client;

import com.msurvey.msurvey.configurations.FeignClientSecurityConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="microservice-groupe-user",path = "/groupe_user",configuration = FeignClientSecurityConfig.class)

public interface GroupeUserFeignClient {
    @GetMapping(value = "/by_membre/{idUser}",consumes = "application/json")
    List<Long> getGroupebyMembre(@PathVariable("idUser")Long idUser);
}
