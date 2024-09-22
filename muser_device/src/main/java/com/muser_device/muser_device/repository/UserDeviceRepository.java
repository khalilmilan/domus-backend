package com.muser_device.muser_device.repository;

import com.muser_device.muser_device.model.UserDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDeviceRepository extends JpaRepository<UserDevice,Long> {
}
