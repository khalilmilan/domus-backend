package com.mproject_user.mproject_user.service.client;

import com.mproject_user.mproject_user.dto.ProjectDTO;
import org.springframework.http.ResponseEntity;

public class ProjectFallback implements ProjectFeignClient{
    @Override
    public ResponseEntity<ProjectDTO> getProject(Long idProject) {
        return null;
    }
}
