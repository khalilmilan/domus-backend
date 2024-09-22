package com.mticket.mticket.service.client;

import com.mticket.mticket.dto.ProjectDTO;
import org.springframework.http.ResponseEntity;

public class ProjectFallback implements ProjectFeignClient{
        @Override
        public ResponseEntity<ProjectDTO> getProject(Long idProject) {
                return null;
        }
}
