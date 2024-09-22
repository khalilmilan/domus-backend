package com.mticket.mticket.model;

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
public class Ticket extends BaseEntity{
    @Id
    @GeneratedValue
    private Long idTicket;
    private String title;
    private String description;
    private Long affectedTo;
    private Integer status;
    private Long idUser;
    private Long idProject;


}
