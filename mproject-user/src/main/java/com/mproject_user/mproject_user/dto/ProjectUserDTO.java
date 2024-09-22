package com.mproject_user.mproject_user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUserDTO {
    private Long idProjectUser;
    private Integer status;
    private Long idProject;
    private Long idUser;
}
