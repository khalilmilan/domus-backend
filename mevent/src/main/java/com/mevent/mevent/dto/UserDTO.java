package com.mevent.mevent.dto;

import com.mevent.mevent.model.auth.UserStatus;
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
        private String email;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private UserStatus userStatus;
       // private UserType userType;
        private String password;
        private String photo;
        private String adresse ;
     //   private UserGender gender;
        private  Long  idGroupe;
        private Date birthDate;

        private Long idRank;
        private Long idRole;
       // private RoleDTO role;


}
