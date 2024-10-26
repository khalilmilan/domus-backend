package com.mevent.mevent.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    private Integer status;
    private SimpleUserDTO user;
    private List<SimpleUserDetailsDTO> participants;
    private List<ForumDTO> forums;

}
