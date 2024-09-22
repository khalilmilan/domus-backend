package com.mgroupe_user.mgroupe_user.service.client;

import com.mgroupe_user.mgroupe_user.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public class UserFallback implements UserFeignClient {
    @Override
    public ResponseEntity<UserDTO> getUser(Long idUser) {
        return null;
    }
}
