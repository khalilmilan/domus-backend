package com.mproject.mproject.service.client;

import com.mproject.mproject.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="microservice-user", fallback = UserFallback.class)
public interface UserFeignClient {

    @GetMapping(value = "/user/{idUser}",consumes = "application/json")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long idUser);

}
