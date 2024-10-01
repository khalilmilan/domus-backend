package com.groupe.mgroupe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GroupeDTO {

    private Long idGroupe;

    private String label;
    private Integer status;
    private Long idUser;
    private SimpleUserDTO user;
    private List<SimpleUserDTO> membres;
}
