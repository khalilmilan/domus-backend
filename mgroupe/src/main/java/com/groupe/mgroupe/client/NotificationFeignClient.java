package com.groupe.mgroupe.client;

import com.groupe.mgroupe.configurations.FeignClientSecurityConfig;
import com.groupe.mgroupe.dto.GroupeUserDTO;
import com.groupe.mgroupe.dto.NotificationDTO;
import com.groupe.mgroupe.dto.SimpleUserDTO;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="microservice-notification",path = "/notification",configuration = FeignClientSecurityConfig.class)

public interface NotificationFeignClient {
    @PostMapping(value = "",consumes = "application/json")
    NotificationDTO saveNotification(@RequestBody NotificationDTO notificationDTO);
}
