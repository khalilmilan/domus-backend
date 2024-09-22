package com.mevent.mevent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventDetailsDTO {

    private Long idEvent;

    private String label;

    private String description;

    private Long idUser;

    private Date date;
    private Integer status;

    private UserDTO user;

    private List<UserDTO> participants;
    private List<ForumDTO> forums;
}
