package com.forum.mforum.controller;

import com.forum.mforum.dto.EventDTO;
import com.forum.mforum.dto.ForumDTO;
import com.forum.mforum.exception.ForumException;
import com.forum.mforum.model.Forum;
import com.forum.mforum.service.ForumService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("forum")
@AllArgsConstructor
public class ForumController {

    private ForumService forumService;
    @GetMapping(value = "/health/readiness")
    public ResponseEntity<String> test(){
        return ResponseEntity.status(HttpStatus.OK).body("hi");
    }
    @PostMapping
    public ResponseEntity<ForumDTO> saveForum(@RequestBody ForumDTO forumDto){
        ForumDTO savedForum = forumService.saveForum(forumDto);
        return new ResponseEntity<>(savedForum, HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllForum() {
        List<ForumDTO> forums = forumService.getALLForum();
        return new ResponseEntity<>(forums, forums.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{idForum}")
    public ResponseEntity<ForumDTO> getForum(@PathVariable("idForum") Long idForum) throws ForumException {
        try {
            return new ResponseEntity<>(forumService.getForum(idForum), HttpStatus.OK);
        } catch (ForumException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ForumDTO());
        }
    }

    @DeleteMapping("/{idForum}")
    public ResponseEntity<?> deleteById(@PathVariable("idForum") Long idForum) throws ForumException{
        try{
            forumService.deleteForum(idForum);
            return new ResponseEntity<>("Successfully deleted forum with id "+idForum, HttpStatus.OK);
        }
        catch (ForumException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idForum}")
    public ResponseEntity<?> updateById(@PathVariable("idForum") Long idForum, @RequestBody Forum forum)
    {
        try {
            forumService.updateForum(idForum,forum);
            return new ResponseEntity<>("Updated forum with id "+idForum+"", HttpStatus.OK);
        }
        catch(ConstraintViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (ForumException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/forums_by_event/{idEvent}")
    public List<ForumDTO> getAllForumByEvent(@PathVariable("idEvent")Long idEvent) {
        List<ForumDTO> forums = forumService.getForumsByEvent(idEvent);
        if(forums.size() > 0){
            return forums;
        }else{
            return new ArrayList<>();
        }
    }
}
