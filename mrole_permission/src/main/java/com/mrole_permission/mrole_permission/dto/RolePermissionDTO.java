package com.mrole_permission.mrole_permission.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionDTO {

    private Long idRolePermission;
    private Long idRole;
    private Long idPermission;
    private Integer status;
}
