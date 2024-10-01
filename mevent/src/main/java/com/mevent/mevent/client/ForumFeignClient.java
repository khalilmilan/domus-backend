package com.mevent.mevent.client;

import com.mevent.mevent.configurations.FeignClientSecurityConfig;
import com.mevent.mevent.dto.EventUserDTO;
import com.mevent.mevent.dto.ForumDTO;
import com.mevent.mevent.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="microservice-forum", path="/forum",configuration = FeignClientSecurityConfig.class)
public interface ForumFeignClient {

    @GetMapping(value = "/{idForum}",consumes = "application/json")
   ForumDTO getForum(@PathVariable("idForum") Long idForum);

    @GetMapping(value = "/forums_by_event/{idEvent}",consumes = "application/json")
    List<ForumDTO> getAllForumByEvent(@PathVariable("idEvent")Long idEvent);

}
