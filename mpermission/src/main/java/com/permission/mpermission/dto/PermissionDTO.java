package com.permission.mpermission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDTO {

    private Long idPermission;

    private String title;

    private String description;

    private Integer status;

    private Long idUser;

    private UserDTO user;
}
