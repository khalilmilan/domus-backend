package com.mproject.mproject.repository;

import com.mproject.mproject.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    List<Project> findProjectsByIdUser(Long idUser);
}
