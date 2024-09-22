package com.mdiscussion.mdiscussion.service.client;

import com.mdiscussion.mdiscussion.dto.MessageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="microservice-message", fallback = MessageFallback.class)
public interface MessageFeignClient {
    @GetMapping(value = "/message/by_discussion/{idDiscussion}",consumes = "application/json")
    ResponseEntity<List<MessageDTO>> getAllMessageByDiscussion(@PathVariable("idDiscussion") Long idDiscussion);
}
