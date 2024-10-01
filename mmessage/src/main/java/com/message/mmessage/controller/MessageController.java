package com.message.mmessage.controller;

import com.message.mmessage.dto.MessageDTO;
import com.message.mmessage.exception.MessageException;
import com.message.mmessage.model.Message;
import com.message.mmessage.service.MessageService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("message")
@AllArgsConstructor
public class MessageController {


    private MessageService messageService;
    @GetMapping(value = "/lowel")
    public String test(){
        return "hi";
    }
    @PostMapping("")
    public MessageDTO saveMessage(@RequestBody MessageDTO messageDto){
        MessageDTO savedMessage = messageService.saveMessage(messageDto);
        return savedMessage;
    }
    @GetMapping("")
    public ResponseEntity<?> getAllMessage() {
        List<MessageDTO> messages = messageService.getALLMessage();
        return new ResponseEntity<>(messages, messages.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{idMessage}")
    public MessageDTO getMessage(@PathVariable("idMessage") Long idMessage) throws MessageException {
        try {
            return messageService.getMessage(idMessage);
        } catch (MessageException e) {
            return new MessageDTO();
        }
    }

    @DeleteMapping("/{idMessage}")
    public void deleteById(@PathVariable("idMessage") Long idMessage) throws MessageException{
        try{
            messageService.deleteMessage(idMessage);

        }
        catch (MessageException e)
        {

        }
    }

    @PutMapping("/{idMessage}")
    public void updateById(@PathVariable("idMessage") Long idMessage, @RequestBody Message message)
    {
        try {
            messageService.updateMessage(idMessage,message);

        }
        catch(ConstraintViolationException e)
        {

        }
        catch (MessageException e) {

        }
    }

    @GetMapping("/by_discussion/{idDiscussion}")
    public List<MessageDTO> getAllMessageByDiscussion(@PathVariable("idDiscussion") Long idDiscussion) {
        List<MessageDTO> messages = messageService.getMessageByDiscussion(idDiscussion);
        return messages;
    }
}
