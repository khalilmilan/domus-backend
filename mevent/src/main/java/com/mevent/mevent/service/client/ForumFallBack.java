package com.mevent.mevent.service.client;

import com.mevent.mevent.dto.EventUserDTO;
import com.mevent.mevent.dto.ForumDTO;
import com.mevent.mevent.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ForumFallBack implements ForumFeignClient{

    @Override
    public ResponseEntity<ForumDTO> getForum(Long idForum) {
        return null;
    }

    @Override
    public ResponseEntity<List<ForumDTO>> getAllForumByEvent(Long idEvent) {
        return null;
    }
}
