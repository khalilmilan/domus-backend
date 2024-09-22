package com.groupe.mgroupe.groupeMapper;

import com.groupe.mgroupe.dto.GroupeDTO;
import com.groupe.mgroupe.dto.UserDTO;
import com.groupe.mgroupe.model.Groupe;

import java.util.ArrayList;

public class GroupeMapper {

    public static GroupeDTO mapToGroupeDto(Groupe groupe){
        GroupeDTO groupeDto = new GroupeDTO(
                groupe.getIdGroupe(),
                groupe.getLabel(),
                groupe.getStatus(),
                groupe.getIdUser(),
                new UserDTO(),
                new ArrayList<>()
        );
        return groupeDto;
    }

    public static Groupe mapToGroupe(GroupeDTO groupeDto){
        Groupe groupe = new Groupe(
                groupeDto.getIdGroupe(),
                groupeDto.getLabel(),
                groupeDto.getStatus(),
                groupeDto.getIdUser()
        );
        return groupe;
    }
}
