package com.mdiscussion.mdiscussion.client;

import com.mdiscussion.mdiscussion.configurations.FeignClientSecurityConfig;
import com.mdiscussion.mdiscussion.dto.MessageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="microservice-message", path = "/message", configuration = FeignClientSecurityConfig.class)
public interface MessageFeignClient {
    @GetMapping(value = "/by_discussion/{idDiscussion}",consumes = "application/json")
    List<MessageDTO> getAllMessageByDiscussion(@PathVariable("idDiscussion") Long idDiscussion);
}
