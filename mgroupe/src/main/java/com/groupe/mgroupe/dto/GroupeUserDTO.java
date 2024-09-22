package com.groupe.mgroupe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GroupeUserDTO {

    private Long idGroupe;
    private Long idUser;
    private Integer status;
}
