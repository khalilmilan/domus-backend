package com.mdiscussion.mdiscussion.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiscussionDTO {
    private Long idDiscussion;
    private Long idUser1;
    private Long idUser2;
    private Long idGroupe;
    private Integer status;
    private SimpleUserDTO user1;
    private SimpleUserDTO user2;
    private List<MessageDTO> messages;

}
