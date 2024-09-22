package com.mgroupe_user.mgroupe_user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupeUser extends BaseEntity{

    @Id
    @GeneratedValue
    private Long idGroupeUser;
    private Long idGroupe;
    private Long idUser;
    private Integer status;
}
