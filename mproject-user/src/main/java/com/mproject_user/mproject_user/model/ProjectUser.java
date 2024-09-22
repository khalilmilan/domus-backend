package com.mproject_user.mproject_user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUser extends BaseEntity{
    @Id
    @GeneratedValue
    private Long idProjectUser;
    private Integer status;
    private Long idProject;
    private Long idUser;

}
