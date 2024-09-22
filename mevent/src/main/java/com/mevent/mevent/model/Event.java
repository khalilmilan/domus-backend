package com.mevent.mevent.model;
import jakarta.persistence.Entity;
import lombok.*;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event extends BaseEntity{
    @Id
    @GeneratedValue
    private Long idEvent;
    private String label;
    private String description;
    private Long idUser;
    private Date date;
    private Integer status;

}
