package com.mproject.mproject.model;

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
public class Project extends BaseEntity{
    @Id
    @GeneratedValue
    private Long idProject;
    private String title;
    private String description;
    private Integer status;
    private Long idUser;

}
