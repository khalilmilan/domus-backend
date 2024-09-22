package com.mevent.mevent.service.client;

import com.mevent.mevent.dto.EventUserDTO;
import com.mevent.mevent.dto.ForumDTO;
import com.mevent.mevent.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="microservice-forum", fallback = ForumFallBack.class)
public interface ForumFeignClient {

    @GetMapping(value = "/forum/{idForum}",consumes = "application/json")
    public ResponseEntity<ForumDTO> getForum(@PathVariable("idForum") Long idForum);

    @GetMapping(value = "/forum/forums_by_event/{idEvent}",consumes = "application/json")
    public ResponseEntity<List<ForumDTO>> getAllForumByEvent(@PathVariable("idEvent")Long idEvent);

}
