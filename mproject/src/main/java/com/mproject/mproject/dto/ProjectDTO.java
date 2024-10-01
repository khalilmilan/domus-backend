package com.mproject.mproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private Long idProject;
    private String title;
    private String description;
    private Integer status;
    private Long idUser;
    private SimpleUserDTO user;
    private List<SimpleUserDTO> membres;
    private List<TicketDTO> tickets;
}
