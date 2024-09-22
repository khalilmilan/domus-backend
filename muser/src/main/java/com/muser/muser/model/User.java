package com.muser.muser.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{

    @Id
    @GeneratedValue
    private Long idUser;
    private String firstName;
    private String lastName;
    private String photo;
    private String password;
    private String email;
    private String adresse ;
    private  String gender;
    private  Long  idGroupe;
    private Date birthDate;
    private String phoneNumber;
    private Integer status;
    private Long idRank;
}
