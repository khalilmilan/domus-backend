package com.mevent.mevent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventUserDTO {

    private Long idEvent;
    private Long idUser;
    private Integer status;
}
