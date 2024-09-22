package com.muser_device.muser_device.mapper;

import com.muser_device.muser_device.dto.UserDeviceDTO;
import com.muser_device.muser_device.model.UserDevice;

import java.util.ArrayList;

public class UserDeviceMapper {

    public static UserDeviceDTO mapToUserDeviceDTO(UserDevice userDevice){
        UserDeviceDTO userDeviceDto = new UserDeviceDTO(
                userDevice.getIdUserDevice(),
                userDevice.getIdUser(),
                userDevice.getName(),
                userDevice.getVersion(),
                userDevice.getUid(),
                userDevice.getToken(),
                userDevice.getStatus()
        );
        return userDeviceDto;
    }

    public static UserDevice mapToUserDevice(UserDeviceDTO deviceDto){
        UserDevice userDevice = new UserDevice(
                deviceDto.getIdUserDevice(),
                deviceDto.getIdUser(),
                deviceDto.getName(),
                deviceDto.getVersion(),
                deviceDto.getUid(),
                deviceDto.getToken(),
                deviceDto.getStatus()
        );
        return userDevice;
    }
}
