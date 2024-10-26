package com.groupe.mgroupe.service;

import com.groupe.mgroupe.dto.GroupeDTO;
import com.groupe.mgroupe.exception.GroupeException;
import com.groupe.mgroupe.model.Groupe;
import org.w3c.dom.events.EventException;

import java.util.List;

public interface GroupeService {
    GroupeDTO saveGroupe(GroupeDTO groupeDto);

    List<GroupeDTO> getALLGroupe();

    void deleteGroupe(Long idGroupe) throws GroupeException;

    GroupeDTO getGroupe(Long idGroupe) throws GroupeException;

    void updateGroupe(Long idGroupe, Groupe groupe) throws GroupeException;

    void addMembre(Long idGroupe, Long idUser) throws GroupeException;

    void removeMembre(Long idGroupe, Long idUser) throws GroupeException;
    List<GroupeDTO> getGroupeByUser(Long idUser) ;
    List<GroupeDTO> getGroupeByMembre(Long idUser);



}
