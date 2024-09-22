package com.mproject_user.mproject_user.dto;

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
        private String email;
        private Integer status;
        private Long idRank;
}
