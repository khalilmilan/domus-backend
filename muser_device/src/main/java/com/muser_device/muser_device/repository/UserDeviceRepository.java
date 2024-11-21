package com.muser_device.muser_device.repository;

import com.muser_device.muser_device.model.UserDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDeviceRepository extends JpaRepository<UserDevice,Long> {

    UserDevice findUserDeviceByIdUserAndUid(Long idUser,String uid);
    List<UserDevice> findUserDevicesByIdUser(Long idUser);
}
