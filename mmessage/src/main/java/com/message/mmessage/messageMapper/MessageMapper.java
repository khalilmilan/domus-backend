package com.message.mmessage.messageMapper;

import com.message.mmessage.dto.MessageDTO;
import com.message.mmessage.dto.UserDTO;
import com.message.mmessage.model.Message;

public class MessageMapper {

    public static MessageDTO mapToMessageDto(Message message){
        MessageDTO messageDto = new MessageDTO(
                message.getIdMessage(),
                message.getContent(),
                message.getStatus(),
                message.getIdSender(),
                message.getIdReciver(),
                message.getIdDiscussion(),
                message.getDate(),
                new UserDTO(),
                new UserDTO()

        );
        return messageDto;
    }

    public static Message mapToMessage(MessageDTO messageDto){
        Message message = new Message(
                messageDto.getIdMessage(),
                messageDto.getContent(),
                messageDto.getStatus(),
                messageDto.getIdSender(),
                messageDto.getIdReciver(),
                messageDto.getIdDiscussion(),
                messageDto.getDate()
        );
        return message;
    }
}
