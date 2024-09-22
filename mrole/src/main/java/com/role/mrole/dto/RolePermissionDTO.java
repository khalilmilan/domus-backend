package com.role.mrole.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionDTO {

    private Long idRole;
    private Long idPermission;
    private Integer status;
}
