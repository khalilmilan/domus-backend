package com.mproject_user.mproject_user.repository;

import com.mproject_user.mproject_user.model.ProjectUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectUserRepository extends JpaRepository<ProjectUser,Long> {
    List<ProjectUser> findProjectUserByIdProject(Long idProject);
    List<ProjectUser> findProjectUserByIdUser(Long idUser);
}
