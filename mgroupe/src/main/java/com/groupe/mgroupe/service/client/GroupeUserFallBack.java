package com.groupe.mgroupe.service.client;

import com.groupe.mgroupe.dto.GroupeUserDTO;
import com.groupe.mgroupe.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class GroupeUserFallBack implements GroupeUserFeignClient{
    @Override
    public ResponseEntity<List<UserDTO>> getAllUser(Long idGroupe) {
        return null;
    }
    @Override
    public ResponseEntity<GroupeUserDTO> saveGroupeUser(GroupeUserDTO groupeUserDTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteByGroupeAndUser(Long idGroupe, Long idUser) {
        return null;
    }
}
