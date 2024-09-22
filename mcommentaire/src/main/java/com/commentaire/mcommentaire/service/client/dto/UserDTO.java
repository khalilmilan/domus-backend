package com.commentaire.mcommentaire.service.client.dto;

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
        private  String  gender;
        private  Long  idGroupe;
        private Date birthDate;
        private String phoneNumber;
        private Integer status;
        private Long idRank;


}
