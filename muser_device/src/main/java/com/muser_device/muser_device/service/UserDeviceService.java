package com.muser_device.muser_device.service;

import com.muser_device.muser_device.dto.UserDeviceDTO;
import com.muser_device.muser_device.exception.UserDeviceException;
import com.muser_device.muser_device.model.UserDevice;

import java.util.List;

public interface UserDeviceService {


    UserDeviceDTO saveUserDevice(UserDeviceDTO userDeviceDto);

    List<UserDeviceDTO> getALLUserDevice();

    void deleteUserDevice(Long idUserDevice) throws UserDeviceException;

    UserDeviceDTO getUserDevice(Long idUserDevice) throws UserDeviceException;

    public void updateUserDevice(Long idUserDevice, UserDevice userDevice) throws UserDeviceException;}
