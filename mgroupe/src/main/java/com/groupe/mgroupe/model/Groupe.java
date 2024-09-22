package com.groupe.mgroupe.model;

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
public class Groupe extends BaseEntity{

    @Id
    @GeneratedValue
    private Long idGroupe;
    private String label;
    private Integer status;
    private Long idUser;
}
