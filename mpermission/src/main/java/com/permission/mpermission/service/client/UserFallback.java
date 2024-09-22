package com.permission.mpermission.service.client;

import com.permission.mpermission.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public class UserFallback implements UserFeignClient{
    @Override
    public ResponseEntity<UserDTO> getUser(Long idUser) {
        return null;
    }
}
