package com.mevent_user.mevent_user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventUserDTO {

    private Long idEventUser;
    private Long idEvent;
    private Long idUser;
    private Integer status;
}
