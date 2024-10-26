package com.mproject.mproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUserDTO {

    private Long idProject;
    private Long idUser;
    private Integer status;
}
