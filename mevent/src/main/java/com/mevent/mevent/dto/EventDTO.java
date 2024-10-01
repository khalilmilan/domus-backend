package com.mevent.mevent.dto;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

    private Long idEvent;
    private String label;
    private String description;
    private Long idUser;
    private Date date;
    private Integer status;
    private SimpleUserDTO user;
    private List<SimpleUserDTO> participants;
    private List<ForumDTO> forums;

}
