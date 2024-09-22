package com.mproject_user.mproject_user.service.client;

import com.mproject_user.mproject_user.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public class UserFallback implements UserFeignClient{
    @Override
    public ResponseEntity<UserDTO> getUser(Long idUser) {
        return null;
    }
}
