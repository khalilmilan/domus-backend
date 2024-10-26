package com.mproject_user.mproject_user.repository;

import com.mproject_user.mproject_user.model.ProjectUser;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectUserRepository extends JpaRepository<ProjectUser,Long> {
    List<ProjectUser> findProjectUserByIdProject(Long idProject);
    List<ProjectUser> findProjectUserByIdUser(Long idUser);
    ProjectUser findProjectUserByIdProjectAndIdUser(Long idProject,Long idUser);
    @Query("SELECT u.idUser FROM ProjectUser u WHERE u.idProject = :projectId")
    List<Long> findUsersInProject(@Param("projectId") Long projectId);

    @Query("SELECT u.idProject FROM ProjectUser u WHERE u.idUser = :idUser")
    List<Long> findProjectByParticipant(@Param("idUSer") Long idUser);
}
