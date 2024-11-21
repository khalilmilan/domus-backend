package com.muser_device.muser_device.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDevice extends BaseEntity{
    @Id
    @GeneratedValue
    private Long idUserDevice;
    private Long idUser;
    private String name;
    private String version;
    private String uid;
    private String token;
    private Integer Status;
    private LocalDateTime lastActive;

}
