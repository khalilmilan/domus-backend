package com.message.mmessage.service.client;

import com.message.mmessage.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public class UserFallback implements UserFeignClient{
    @Override
    public ResponseEntity<UserDTO> getUser(Long idUser) {
        return null;
    }
}
