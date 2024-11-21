package com.groupe.mgroupe.client;

import com.groupe.mgroupe.configurations.FeignClientSecurityConfig;
import com.groupe.mgroupe.dto.SimpleUserDTO;
import com.groupe.mgroupe.dto.UserDeviceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="microservice-user-device",path = "/user_device",configuration = FeignClientSecurityConfig.class)

public interface UserDeviceFeignClient {


    @GetMapping(value = "/get_device/{idUser}",consumes = "application/json")
    List<UserDeviceDTO> getDevice(@PathVariable("idUser")Long idUser);
}
