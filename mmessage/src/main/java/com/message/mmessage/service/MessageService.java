package com.message.mmessage.service;

import com.message.mmessage.dto.MessageDTO;
import com.message.mmessage.exception.MessageException;
import com.message.mmessage.model.Message;

import java.util.List;

public interface MessageService {

    MessageDTO saveMessage(MessageDTO messageDto);

    List<MessageDTO> getALLMessage();

    void deleteMessage(Long idMessage) throws MessageException;

    MessageDTO getMessage(Long idMessage) throws MessageException;

     void updateMessage(Long id, Message message) throws MessageException;
     
     List<MessageDTO> getMessageByDiscussion(Long idDiscussion);
}
