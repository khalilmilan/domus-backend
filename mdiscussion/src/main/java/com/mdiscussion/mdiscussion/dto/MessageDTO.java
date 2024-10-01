package com.mdiscussion.mdiscussion.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {

    private Long idMessage;
    private String content;
    private Long idSender;
    private Long idReciver;
    private Date date;
    private SimpleUserDTO reciver;
    private SimpleUserDTO sender;
}
