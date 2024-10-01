package com.message.mmessage.service;

import com.message.mmessage.client.UserServiceClient;
import com.message.mmessage.dto.MessageDTO;
import com.message.mmessage.dto.SimpleUserDTO;
import com.message.mmessage.exception.MessageException;
import com.message.mmessage.messageMapper.MessageMapper;
import com.message.mmessage.model.Message;
import com.message.mmessage.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class MessageServiceImple implements MessageService{

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImple.class);

    private MessageRepository messageRepository;

    private UserServiceClient userFeignClient;
    @Override
    public MessageDTO saveMessage(MessageDTO messageDto) {
        Message message = MessageMapper.mapToMessage(messageDto);
        message.setDate(new Date());
        Message savedMapper = messageRepository.save(message);
        MessageDTO savedMessagheDto = MessageMapper.mapToMessageDto(savedMapper);
        return savedMessagheDto;
    }

    @Override
    public List<MessageDTO> getALLMessage() {
        List<Message> messages = messageRepository.findAll();
        List<MessageDTO> messageDto= new ArrayList<>();
        if (messages.size() > 0) {
            for (Message message : messages) {
                MessageDTO dto = MessageMapper.mapToMessageDto(message);
                messageDto.add(dto);
            }
            return messageDto;
        }else {
            return new ArrayList<MessageDTO>();
        }
    }

    @Override
    public void deleteMessage(Long idMessage) throws MessageException {
        Optional<Message> messageOptional = messageRepository.findById(idMessage);
        if (!messageOptional.isPresent()) {
            throw new MessageException(MessageException.NotFoundException(idMessage));
        } else {
            messageRepository.deleteById(idMessage);
        }
    }

    @Override
    public MessageDTO getMessage(Long idMessage) throws MessageException {
        Optional<Message> messageOptional = messageRepository.findById(idMessage);
        if (!messageOptional.isPresent()) {
            throw new MessageException(MessageException.NotFoundException(idMessage));
        }else {
            MessageDTO dto =MessageMapper.mapToMessageDto(messageOptional.get());
            SimpleUserDTO sender = userFeignClient.getSimpleUser(dto.getIdSender());
            dto.setSender(sender);
            SimpleUserDTO reciver = userFeignClient.getSimpleUser(dto.getIdSender());
            dto.setReciver(reciver);
            return dto;
        }
    }

    @Override
    public void updateMessage(Long id, Message message) throws MessageException {
        Optional<Message> messageWithId = messageRepository.findById(id);
        if(messageWithId.isPresent())
        {
            Message messageToUpdate=messageWithId.get();
            messageToUpdate.setContent(message.getContent());
            messageRepository.save(messageToUpdate);
        }
        else
        {
            throw new MessageException(MessageException.NotFoundException(id));
        }
    }

    @Override
    public List<MessageDTO> getMessageByDiscussion(Long idDiscussion) {
        List<Message> messages = messageRepository.findMessageByIdDiscussionOrderByDate(idDiscussion);
        List<MessageDTO> messageDto= new ArrayList<>();
        if (messages.size() > 0) {
            for (Message message : messages) {
                MessageDTO dto = MessageMapper.mapToMessageDto(message);
                SimpleUserDTO sender = userFeignClient.getSimpleUser(dto.getIdSender());
                dto.setSender(sender);
                SimpleUserDTO reciver = userFeignClient.getSimpleUser(dto.getIdSender());
                dto.setReciver(reciver);
                messageDto.add(dto);
            }
            return messageDto;
        }else {
            return new ArrayList<MessageDTO>();
        }
    }
}
