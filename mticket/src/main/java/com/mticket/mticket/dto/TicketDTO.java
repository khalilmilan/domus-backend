package com.mticket.mticket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    private Long idTicket;
    private String title;
    private String description;
    private Long affectedTo;
    private Integer status;
    private Long idUser;
    private Long idProject;
    private SimpleUserDTO UserAffectedTo;
    private SimpleUserDTO user;
    private ProjectDTO project;
}
