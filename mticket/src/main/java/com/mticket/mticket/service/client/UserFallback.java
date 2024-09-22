package com.mticket.mticket.service.client;

import com.mticket.mticket.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public class UserFallback implements UserFeignClient{
    @Override
    public ResponseEntity<UserDTO> getUser(Long idUser) {
        return null;
    }
}
