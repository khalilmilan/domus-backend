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
    @PostMapping
    public ResponseEntity<MessageDTO> saveMessage(@RequestBody MessageDTO messageDto){
        MessageDTO savedMessage = messageService.saveMessage(messageDto);
        return new ResponseEntity<>(savedMessage, HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllMessage() {
        List<MessageDTO> messages = messageService.getALLMessage();
        return new ResponseEntity<>(messages, messages.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{idMessage}")
    public ResponseEntity<?> getMessage(@PathVariable("idMessage") Long idMessage) throws MessageException {
        try {
            return new ResponseEntity<>(messageService.getMessage(idMessage), HttpStatus.OK);
        } catch (MessageException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idMessage}")
    public ResponseEntity<?> deleteById(@PathVariable("idMessage") Long idMessage) throws MessageException{
        try{
            messageService.deleteMessage(idMessage);
            return new ResponseEntity<>("Successfully deleted message with id "+idMessage, HttpStatus.OK);
        }
        catch (MessageException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idMessage}")
    public ResponseEntity<?> updateById(@PathVariable("idMessage") Long idMessage, @RequestBody Message message)
    {
        try {
            messageService.updateMessage(idMessage,message);
            return new ResponseEntity<>("Updated message with id "+idMessage+"", HttpStatus.OK);
        }
        catch(ConstraintViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (MessageException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by_discussion/{idDiscussion}")
    public ResponseEntity<List<MessageDTO>> getAllMessageByDiscussion(@PathVariable("idDiscussion") Long idDiscussion) {
        List<MessageDTO> messages = messageService.getMessageByDiscussion(idDiscussion);
        return new ResponseEntity<>(messages, messages.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
