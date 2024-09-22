package com.groupe.mgroupe.service;

import com.groupe.mgroupe.dto.GroupeDTO;
import com.groupe.mgroupe.dto.GroupeUserDTO;
import com.groupe.mgroupe.dto.UserDTO;
import com.groupe.mgroupe.exception.GroupeException;
import com.groupe.mgroupe.groupeMapper.GroupeMapper;
import com.groupe.mgroupe.model.Groupe;
import com.groupe.mgroupe.repository.GroupeRepository;
import com.groupe.mgroupe.service.client.GroupeUserFeignClient;
import com.groupe.mgroupe.service.client.UserFeignClient;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.events.EventException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GroupeServiceImple implements GroupeService{

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupeServiceImple.class);

    private GroupeRepository groupeRepository;
    private UserFeignClient userFeignClient;
    private GroupeUserFeignClient groupeUserFeignClient;

    @Override
    public GroupeDTO saveGroupe(GroupeDTO groupeDto) {
        Groupe groupe = GroupeMapper.mapToGroupe(groupeDto);
        Groupe savedGroupe = groupeRepository.save(groupe);
        GroupeDTO savedGroupeDto = GroupeMapper.mapToGroupeDto(savedGroupe);
        return savedGroupeDto;
    }

    @Override
    public List<GroupeDTO> getALLGroupe() {
        List<Groupe> groupes = groupeRepository.findAll();
        List<GroupeDTO> groupeDto= new ArrayList<>();
        if (groupes.size() > 0) {
            for (Groupe groupe : groupes) {
                GroupeDTO dto = GroupeMapper.mapToGroupeDto(groupe);
                groupeDto.add(dto);
            }
            return groupeDto;
        }else {
            return new ArrayList<GroupeDTO>();
        }
    }

    @Override
    public void deleteGroupe(Long idGroupe) throws GroupeException {
        Optional<Groupe> groupeOptional = groupeRepository.findById(idGroupe);
        if (!groupeOptional.isPresent()) {
            throw new GroupeException(GroupeException.NotFoundException(idGroupe));
        } else {
            groupeRepository.deleteById(idGroupe);
        }
    }

    @Override
    public GroupeDTO getGroupe(Long idGroupe) throws GroupeException {
        Optional<Groupe> groupeOptional = groupeRepository.findById(idGroupe);
        if (!groupeOptional.isPresent()) {
            throw new GroupeException(GroupeException.NotFoundException(idGroupe));
        }else {
            GroupeDTO dto =GroupeMapper.mapToGroupeDto(groupeOptional.get());
            UserDTO userDto = userFeignClient.getUser(dto.getIdUser()).getBody();
            dto.setUser(userDto);
            List<UserDTO> membres =  groupeUserFeignClient.getAllUser(dto.getIdGroupe()).getBody();
            dto.setMembres(membres);
            return dto;
        }
    }

    @Override
    public void updateGroupe(Long idGroupe, Groupe groupe) throws GroupeException {
        Optional<Groupe> groupeWithId = groupeRepository.findById(idGroupe);
        if(groupeWithId.isPresent())
        {
            Groupe groupeToUpdate=groupeWithId.get();
            groupeToUpdate.setLabel(groupe.getLabel());
            groupeRepository.save(groupeToUpdate);
        }
        else
        {
            throw new GroupeException(GroupeException.NotFoundException(idGroupe));
        }
    }

    @Override
    public void addMembre(Long idGroupe, Long idUser) throws GroupeException {
        Optional<Groupe> groupeWithId = groupeRepository.findById(idGroupe);
        if(groupeWithId.isPresent())
        {
            GroupeUserDTO eventUserDto = new GroupeUserDTO(
                    idGroupe,
                    idUser,
                    1
            );
             groupeUserFeignClient.saveGroupeUser(eventUserDto).getBody();
        }
        else
        {
            throw new GroupeException(GroupeException.NotFoundException(idGroupe));
        }
    }

    @Override
    public void removeMembre(Long idGroupe, Long idUser) throws GroupeException {

        Optional<Groupe> groupeWithId = groupeRepository.findById(idGroupe);
        if(groupeWithId.isPresent())
        {

            groupeUserFeignClient.deleteByGroupeAndUser(idGroupe,idUser).getBody();
        }
        else
        {
            throw new GroupeException(GroupeException.NotFoundException(idGroupe));
        }
    }
}
