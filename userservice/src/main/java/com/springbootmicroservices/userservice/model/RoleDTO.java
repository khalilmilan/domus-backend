package com.springbootmicroservices.userservice.model;

import com.springbootmicroservices.userservice.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private Long idRole;
    private String title;
    private String description;
    private Integer status;
    private Long idUser;
    private User user;
    private List<PermissionDTO> permissions;

}
