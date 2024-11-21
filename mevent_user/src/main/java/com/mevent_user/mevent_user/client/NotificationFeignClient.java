package com.mevent_user.mevent_user.client;



import com.mevent_user.mevent_user.configurations.FeignClientSecurityConfig;
import com.mevent_user.mevent_user.dto.NotificationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="microservice-notification",path = "/notification",configuration = FeignClientSecurityConfig.class)

public interface NotificationFeignClient {
    @PostMapping(value = "",consumes = "application/json")
    NotificationDTO saveNotification(@RequestBody NotificationDTO notificationDTO);
}
