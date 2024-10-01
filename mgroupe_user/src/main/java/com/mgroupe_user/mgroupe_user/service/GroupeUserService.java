package com.mgroupe_user.mgroupe_user.service;


import com.mgroupe_user.mgroupe_user.dto.GroupeUserDTO;
import com.mgroupe_user.mgroupe_user.dto.SimpleUserDTO;
import com.mgroupe_user.mgroupe_user.exception.GroupeUserException;
import com.mgroupe_user.mgroupe_user.model.GroupeUser;

import java.util.List;

public interface GroupeUserService {

    GroupeUserDTO saveGroupeUser(GroupeUserDTO groupeUserDto);

    List<GroupeUserDTO> getALLGroupeUser();

    void deleteGroupeUser(Long idGroupeUser) throws GroupeUserException;

    GroupeUserDTO getGroupeUser(Long idGroupeUser) throws GroupeUserException;

    public void updateGroupeUser(Long id, GroupeUser groupe) throws GroupeUserException;

    public List<SimpleUserDTO> getGroupeUserByGroupe(Long idGroupe);
    public void deleteGroupeUserByIdGroupeIdUser(Long idGroupe,Long idUser) throws GroupeUserException;
}
