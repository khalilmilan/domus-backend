package com.mdiscussion.mdiscussion.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")

    private Date date;
    private SimpleUserDTO reciver;
    private SimpleUserDTO sender;
}
