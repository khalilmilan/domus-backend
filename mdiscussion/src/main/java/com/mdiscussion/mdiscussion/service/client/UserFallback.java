package com.mdiscussion.mdiscussion.service.client;

import com.mdiscussion.mdiscussion.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public class UserFallback implements UserFeignClient{
    @Override
    public ResponseEntity<UserDTO> getUser(Long idUser) {
        return null;
    }
}
