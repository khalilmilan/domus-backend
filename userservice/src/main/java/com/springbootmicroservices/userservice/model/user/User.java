package com.springbootmicroservices.userservice.model.user;

import com.springbootmicroservices.userservice.model.RoleDTO;
import com.springbootmicroservices.userservice.model.common.BaseDomainModel;
import com.springbootmicroservices.userservice.model.user.enums.UserGender;
import com.springbootmicroservices.userservice.model.user.enums.UserStatus;
import com.springbootmicroservices.userservice.model.user.enums.UserType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * Represents a user domain object named {@link User} in the system.
 * The {@code User} class is a domain model that contains user-related information such as
 * identification, contact details, status, type, and password. It extends {@link BaseDomainModel}
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseDomainModel {

    private Long idUser;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private UserStatus userStatus;
    private UserType userType;
    private String password;
    private String photo;
    private String adresse ;
    private UserGender gender;
    private  Long  idGroupe;
    private Date birthDate;

    private Long idRank;
    private Long idRole;
    private RoleDTO role;
}
