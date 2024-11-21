package com.mproject_user.mproject_user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDeviceDTO {
    private Long idUserDevice;
    private Long idUser;
    private String name;
    private String version;
    private String uid;
    private String token;
    private Integer Status;
    private LocalDateTime lastActive;

}
