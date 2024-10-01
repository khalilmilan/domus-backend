package com.muser.muser.dto;

import com.muser.muser.model.enums.UserGender;
import com.muser.muser.model.enums.UserStatus;
import com.muser.muser.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long idUser;
    private String firstName;
    private String lastName;
    private String photo;
    private String password;
    private String email;
    private String adresse ;
    private UserGender gender;
    private  Long  idGroupe;
    private Date birthDate;
    private String phoneNumber;
    private UserStatus status;
    private UserType userType;
    private Long idRank;
    private Long idRole;
    private RoleDTO role;
}
