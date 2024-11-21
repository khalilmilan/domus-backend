package com.msurvey.msurvey.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleGroupeDTO {
    private Long idGroupe;
    private String label;
    private Integer status;
    private Long idUser;
    private SimpleUserDTO user;
}
