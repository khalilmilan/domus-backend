package com.mgroupe_user.mgroupe_user.service;


import com.mgroupe_user.mgroupe_user.client.UserServiceClient;
import com.mgroupe_user.mgroupe_user.dto.GroupeUserDTO;
import com.mgroupe_user.mgroupe_user.dto.SimpleUserDTO;
import com.mgroupe_user.mgroupe_user.exception.GroupeUserException;
import com.mgroupe_user.mgroupe_user.mapper.GroupeUserMapper;
import com.mgroupe_user.mgroupe_user.model.GroupeUser;
import com.mgroupe_user.mgroupe_user.repository.GroupeUserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class GroupeUserImple implements GroupeUserService{

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupeUserImple.class);
    private UserServiceClient userFeignClient;
    private GroupeUserRepository groupeUserRepository;

    @Override
    public GroupeUserDTO saveGroupeUser(GroupeUserDTO groupeUserDto) {
        GroupeUser groupeUser = GroupeUserMapper.mapToGroupeUser(groupeUserDto);
        GroupeUser savedGroupeUser = groupeUserRepository.save(groupeUser);
        GroupeUserDTO savedGroupeUserDto = GroupeUserMapper.mapToGroupeUserDto(savedGroupeUser);
        return savedGroupeUserDto;
    }

    @Override
    public List<GroupeUserDTO> getALLGroupeUser() {
        List<GroupeUser> groupesUser = groupeUserRepository.findAll();
        List<GroupeUserDTO> groupeUserDto= new ArrayList<>();
        if (groupesUser.size() > 0) {
            for (GroupeUser groupe : groupesUser) {
                GroupeUserDTO dto =GroupeUserMapper.mapToGroupeUserDto(groupe);
                groupeUserDto.add(dto);
            }
            return groupeUserDto;
        }else {
            return new ArrayList<GroupeUserDTO>();
        }
    }

    @Override
    public void deleteGroupeUser(Long idGroupeUser) throws GroupeUserException {
        Optional<GroupeUser> groupeUserOptional = groupeUserRepository.findById(idGroupeUser);
        if (!groupeUserOptional.isPresent()) {
            throw new GroupeUserException(GroupeUserException.NotFoundException(idGroupeUser));
        } else {
            groupeUserRepository.deleteById(idGroupeUser);
        }
    }

    @Override
    public GroupeUserDTO getGroupeUser(Long idGroupeUser) throws GroupeUserException {
        Optional<GroupeUser> groupeUserOptional = groupeUserRepository.findById(idGroupeUser);
        if (!groupeUserOptional.isPresent()) {
            throw new GroupeUserException(GroupeUserException.NotFoundException(idGroupeUser));
        }else {
            GroupeUserDTO dto =GroupeUserMapper.mapToGroupeUserDto(groupeUserOptional.get());
            return dto;
        }
    }

    @Override
    public void updateGroupeUser(Long id, GroupeUser groupe) throws GroupeUserException {
        Optional<GroupeUser> groupeUserWithId = groupeUserRepository.findById(id);
        if(groupeUserWithId.isPresent())
        {
            GroupeUser groupeUserToUpdate=groupeUserWithId.get();
            groupeUserToUpdate.setIdGroupe(groupe.getIdGroupe());
            groupeUserToUpdate.setIdUser(groupe.getIdUser());
            groupeUserToUpdate.setStatus(groupe.getStatus());
            groupeUserRepository.save(groupeUserToUpdate);
        }
        else
        {
            throw new GroupeUserException(GroupeUserException.NotFoundException(id));
        }
    }

    @Override
    public List<SimpleUserDTO> getGroupeUserByGroupe(Long idGroupe) {
        List<GroupeUser> groupes = groupeUserRepository.findGroupeUserByIdGroupe(idGroupe);;
        List<SimpleUserDTO> userDtos = new ArrayList<>();
        if (groupes.size() > 0) {
            for (GroupeUser groupe : groupes) {
                GroupeUserDTO dto =GroupeUserMapper.mapToGroupeUserDto(groupe);
                SimpleUserDTO userDto =  userFeignClient.getSimpleUser(dto.getIdUser());
                userDtos.add(userDto);
            }
            return userDtos;
        }else {
            return new ArrayList<SimpleUserDTO>();
        }
    }

    @Override
    public void deleteGroupeUserByIdGroupeIdUser(Long idGroupe, Long idUser) throws GroupeUserException {
        Optional<GroupeUser> groupeUserOptional = Optional.ofNullable(groupeUserRepository.findGroupeUserByIdGroupeAndIdUser(idGroupe, idUser));
        if (!groupeUserOptional.isPresent()) {
            throw new GroupeUserException(GroupeUserException.NotFoundException(idGroupe));
        } else {
            groupeUserRepository.deleteById(groupeUserOptional.get().getIdGroupeUser());
        }
    }
}
