package com.mdiscussion.mdiscussion.model;

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
public class Discussion extends BaseEntity{
    @Id
    @GeneratedValue
    private Long idDiscussion;
    private Long idUser1;
    private Long idUser2;
    private String idFireBase;
    private Long idGroupe;
    private Integer status;

}
