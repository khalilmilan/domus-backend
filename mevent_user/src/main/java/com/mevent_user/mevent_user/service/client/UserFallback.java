package com.mevent_user.mevent_user.service.client;

import com.mevent_user.mevent_user.dto.UserDTO;
import com.mevent_user.mevent_user.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public class UserFallback implements UserFeignClient{
    @Override
    public ResponseEntity<UserDTO> getUser(Long idUser) {
        return null;
    }
}
