package com.mproject.mproject.service.client;

import com.mproject.mproject.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ProjectUserFallback implements ProjectUserFeignClient{

        @Override
        public ResponseEntity<List<UserDTO>> getProjectUserByProject(Long idProject) {
                return null;
        }
}
