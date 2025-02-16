package com.groupe.mgroupe.service;

import com.groupe.mgroupe.client.GroupeUserFeignClient;
import com.groupe.mgroupe.client.NotificationFeignClient;
import com.groupe.mgroupe.client.UserDeviceFeignClient;
import com.groupe.mgroupe.client.UserServiceClient;
import com.groupe.mgroupe.dto.*;
import com.groupe.mgroupe.exception.GroupeException;
import com.groupe.mgroupe.groupeMapper.GroupeMapper;
import com.groupe.mgroupe.model.Groupe;
import com.groupe.mgroupe.repository.GroupeRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GroupeServiceImple implements GroupeService{

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupeServiceImple.class);

    private GroupeRepository groupeRepository;
    private UserServiceClient userFeignClient;
    private GroupeUserFeignClient groupeUserFeignClient;
    private UserDeviceFeignClient userDeviceFeignClient;

    private NotificationFeignClient notificationFeignClient;
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
            SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
            dto.setUser(userDto);
            List<SimpleUserDTO> membres =  groupeUserFeignClient.getAllUser(dto.getIdGroupe());
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
            GroupeUserDTO groupeUserDto = new GroupeUserDTO(
                    idGroupe,
                    idUser,
                    1
            );
            groupeUserFeignClient.saveGroupeUser(groupeUserDto);
            List<UserDeviceDTO> devices = userDeviceFeignClient.getDevice(idUser);
            for (UserDeviceDTO device:devices){
                NotificationDTO notification = new NotificationDTO();
                notification.setTitle("👥 New group");
                notification.setDescription("You have been added to groupe "+groupeWithId.get().getLabel());
                notification.setType(1);
                notification.setToken(device.getToken());
                notification.setBadgeCount(1);
                notification.setIdReciver(idUser);
                notification.setImageUrl("https://images.unsplash.com/photo-1517423440428-a5a00ad493e8");
                notification.setDate(new Date());
                notificationFeignClient.saveNotification(notification);
            }

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
            groupeUserFeignClient.deleteByGroupeAndUser(idGroupe,idUser);
            List<UserDeviceDTO> devices = userDeviceFeignClient.getDevice(idUser);
            for (UserDeviceDTO device:devices){
                NotificationDTO notification = new NotificationDTO();
                notification.setTitle("🚫 Excluded from Groupe");
                notification.setDescription("You are excluded from groupe "+groupeWithId.get().getLabel());
                notification.setToken(device.getToken());
                notification.setType(2);
                notification.setBadgeCount(1);
                notification.setIdReciver(idUser);
                notification.setImageUrl("https://images.unsplash.com/photo-1517423440428-a5a00ad493e8");
                notification.setDate(new Date());
                notificationFeignClient.saveNotification(notification);
            }
        }
        else
        {
            throw new GroupeException(GroupeException.NotFoundException(idGroupe));
        }
    }

    @Override
    public List<GroupeDTO> getGroupeByUser(Long idUser) {
        List<Groupe> groupes = groupeRepository.findGroupesByIdUser(idUser);
        List<GroupeDTO> groupeDto= new ArrayList<>();
        if (groupes.size() > 0) {
            for (Groupe groupe : groupes) {
                GroupeDTO dto = GroupeMapper.mapToGroupeDto(groupe);
                SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
                dto.setUser(userDto);
                List<SimpleUserDTO> membres =  groupeUserFeignClient.getAllUser(dto.getIdGroupe());
                dto.setMembres(membres);
                groupeDto.add(dto);
            }
            return groupeDto;
        }else {
            return new ArrayList<GroupeDTO>();
        }
    }

    @Override
    public List<GroupeDTO> getGroupeByMembre(Long idUser) {
        List<Long> idsGroupe = groupeUserFeignClient.getGroupebyMembre(idUser);
        List<Groupe> groupes = groupeRepository.findAllById(idsGroupe);
        List<GroupeDTO> groupeDto= new ArrayList<>();
        if (groupes.size() > 0) {
            for (Groupe groupe : groupes) {
                GroupeDTO dto = GroupeMapper.mapToGroupeDto(groupe);
                SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
                dto.setUser(userDto);
                List<SimpleUserDTO> membres =  groupeUserFeignClient.getAllUser(dto.getIdGroupe());
                dto.setMembres(membres);
                groupeDto.add(dto);
            }
            return groupeDto;
        }else {
            return new ArrayList<GroupeDTO>();
        }
    }

        @Override
        public void leaveGroupe(Long idGroupe, Long idUser) throws GroupeException {
            Optional<Groupe> groupeWithId = groupeRepository.findById(idGroupe);
            if(groupeWithId.isPresent())
            {
                groupeUserFeignClient.deleteByGroupeAndUser(idGroupe,idUser);
                SimpleUserDTO member = userFeignClient.getSimpleUser(idUser);
                List<UserDeviceDTO> devices = userDeviceFeignClient.getDevice(groupeWithId.get().getIdUser());
                for (UserDeviceDTO device:devices){
                    NotificationDTO notification = new NotificationDTO();
                    notification.setTitle("👤 Member Left the Group");
                    notification.setDescription( member.getFirstName()+ " "+member.getLastName()+" left the group "+groupeWithId.get().getLabel());
                    notification.setToken(device.getToken());
                    notification.setDate(new Date());
                    notification.setBadgeCount(1);
                    notification.setType(3);
                    notification.setIdReciver(groupeWithId.get().getIdUser());
                    notification.setImageUrl("https://images.unsplash.com/photo-1517423440428-a5a00ad493e8");
                    notificationFeignClient.saveNotification(notification);
                }
            }
            else
            {
                throw new GroupeException(GroupeException.NotFoundException(idGroupe));
            }
        }

    @Override
    public SimpleGroupeDTO getSimpleGroupe(Long idGroupe) throws GroupeException {
        Optional<Groupe> groupeOptional = groupeRepository.findById(idGroupe);
        if (!groupeOptional.isPresent()) {
            throw new GroupeException(GroupeException.NotFoundException(idGroupe));
        }else {
            SimpleGroupeDTO dto =GroupeMapper.mapToSimpleGroupeDto(groupeOptional.get());
            SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
            dto.setUser(userDto);
            return dto;
        }
    }

}

