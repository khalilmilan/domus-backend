package com.muser_device.muser_device.service;

import com.muser_device.muser_device.dto.UserDeviceDTO;
import com.muser_device.muser_device.exception.UserDeviceException;
import com.muser_device.muser_device.mapper.UserDeviceMapper;
import com.muser_device.muser_device.model.UserDevice;
import com.muser_device.muser_device.repository.UserDeviceRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class UserDeviceServiceImple implements UserDeviceService{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDeviceServiceImple.class);

    private UserDeviceRepository userDeviceRepository;
    @Override
    public UserDeviceDTO saveUserDevice(UserDeviceDTO userDeviceDto) {
        UserDevice device =  userDeviceRepository.findUserDeviceByIdUserAndUid(userDeviceDto.getIdUser(),userDeviceDto.getUid());
        if(device ==null){
            UserDevice userDevice = UserDeviceMapper.mapToUserDevice(userDeviceDto);
            userDevice.setLastActive(LocalDateTime.now());
            UserDevice savedUserDevice = userDeviceRepository.save(userDevice);
            UserDeviceDTO savedUserDeviceDto = UserDeviceMapper.mapToUserDeviceDTO(savedUserDevice);
            return savedUserDeviceDto;
        }else{
            device.setToken(userDeviceDto.getToken());
            device.setVersion(userDeviceDto.getVersion());
            device.setLastActive(LocalDateTime.now());
            UserDevice updatredDevice = userDeviceRepository.save(device);
            UserDeviceDTO savedUserDeviceDto = UserDeviceMapper.mapToUserDeviceDTO(updatredDevice);
            return savedUserDeviceDto;
        }

    }

    @Override
    public List<UserDeviceDTO> getALLUserDevice() {
        List<UserDevice> usersDevice = userDeviceRepository.findAll();
        List<UserDeviceDTO> usersDeviceDto= new ArrayList<>();
        if (usersDevice.size() > 0) {
            for (UserDevice  userDevice: usersDevice) {
                UserDeviceDTO dto = UserDeviceMapper.mapToUserDeviceDTO(userDevice);
                usersDeviceDto.add(dto);
            }
            return usersDeviceDto;
        }else {
            return new ArrayList<UserDeviceDTO>();
        }
    }

    @Override
    public void deleteUserDevice(Long idUserDevice) throws UserDeviceException {
        Optional<UserDevice> userDeviceOptional = userDeviceRepository.findById(idUserDevice);
        if (!userDeviceOptional.isPresent()) {
            throw new UserDeviceException(UserDeviceException.NotFoundException(idUserDevice));
        } else {
            userDeviceRepository.deleteById(idUserDevice);
        }
    }

    @Override
    public UserDeviceDTO getUserDevice(Long idUserDevice) throws UserDeviceException {
        Optional<UserDevice> userDeviceOptional = userDeviceRepository.findById(idUserDevice);
        if (!userDeviceOptional.isPresent()) {
            throw new UserDeviceException(UserDeviceException.NotFoundException(idUserDevice));
        }else {
            UserDeviceDTO dto =UserDeviceMapper.mapToUserDeviceDTO(userDeviceOptional.get());
            return dto;
        }
    }

    @Override
    public void updateUserDevice(Long idUserDevice, UserDevice userDevice) throws UserDeviceException {
        Optional<UserDevice> userDeviceWithId = userDeviceRepository.findById(idUserDevice);
        if(userDeviceWithId.isPresent())
        {
            UserDevice userDeviceToUpdate=userDeviceWithId.get();
            userDeviceToUpdate.setIdUser(userDevice.getIdUser());
            userDeviceToUpdate.setName(userDevice.getName());
            userDeviceToUpdate.setVersion(userDevice.getVersion());
            userDeviceToUpdate.setUid(userDevice.getUid());
            userDeviceToUpdate.setToken(userDevice.getToken());
            userDeviceToUpdate.setStatus(userDevice.getStatus());
            userDeviceRepository.save(userDeviceToUpdate);
        }
        else
        {
            throw new UserDeviceException(UserDeviceException.NotFoundException(idUserDevice));
        }
    }

    @Override
    public List<UserDeviceDTO> getUSerDeviceByIdUser(Long idUser) {
        List<UserDevice> userDevices = userDeviceRepository.findUserDevicesByIdUser(idUser);
        List<UserDeviceDTO> dto = new ArrayList<>();
        for(UserDevice userDevice:userDevices){
           UserDeviceDTO userDeviceDTO = UserDeviceMapper.mapToUserDeviceDTO(userDevice);
            dto.add(userDeviceDTO);
        }
        return dto;
    }
}
