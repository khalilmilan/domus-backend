package com.msurvey_values.msurvey_values.service.client;

import com.msurvey_values.msurvey_values.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public class UserFallback implements UserFeignClient{
    @Override
    public ResponseEntity<UserDTO> getUser(Long idUser) {
        return null;
    }
}
