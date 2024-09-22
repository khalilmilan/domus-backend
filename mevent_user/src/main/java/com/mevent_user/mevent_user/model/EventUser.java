package com.mevent_user.mevent_user.model;

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
public class EventUser extends BaseEntity{
    @Id
    @GeneratedValue
    private Long idEventUser;
    private Long idEvent;
    private Long idUser;
    private Integer status;


}
