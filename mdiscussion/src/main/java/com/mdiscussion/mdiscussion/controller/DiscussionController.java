package com.mdiscussion.mdiscussion.controller;

import com.mdiscussion.mdiscussion.dto.DiscussionDTO;
import com.mdiscussion.mdiscussion.dto.SimpleUserDTO;
import com.mdiscussion.mdiscussion.exception.DiscussionException;
import com.mdiscussion.mdiscussion.model.Discussion;
import com.mdiscussion.mdiscussion.service.DiscussionService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("discussion")
@AllArgsConstructor
public class DiscussionController {
    private DiscussionService discussionService;
    @GetMapping(value = "/health/readiness")
    public ResponseEntity<String> test(){
        return ResponseEntity.status(HttpStatus.OK).body("hi");
    }
    @PostMapping
    public DiscussionDTO saveDiscussion(@RequestBody DiscussionDTO DiscussionDto){
        return discussionService.saveDiscussion(DiscussionDto);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllDiscussion() {
        List<DiscussionDTO> discussions = discussionService.getALLDiscussion();
        return new ResponseEntity<>(discussions, discussions.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{idDiscussion}")
    public ResponseEntity<DiscussionDTO> getDiscussion(@PathVariable("idDiscussion") Long idDiscussion) throws DiscussionException {
        try {
            return new ResponseEntity<>(discussionService.getDiscussion(idDiscussion), HttpStatus.OK);
        } catch (DiscussionException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DiscussionDTO());
        }
    }

    @DeleteMapping("/{idDiscussion}")
    public ResponseEntity<?> deleteById(@PathVariable("idDiscussion") Long idDiscussion) throws DiscussionException{
        try{
            discussionService.deleteDiscussion(idDiscussion);
            return new ResponseEntity<>("Successfully deleted discussion with id "+idDiscussion, HttpStatus.OK);
        }
        catch (DiscussionException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idDiscussion}")
    public ResponseEntity<?> updateById(@PathVariable("idDiscussion") Long idDiscussion, @RequestBody Discussion discussion)
    {
        try {
            discussionService.updateDiscussion(idDiscussion,discussion);
            return new ResponseEntity<>("Updated discussion with id "+idDiscussion+"", HttpStatus.OK);
        }
        catch(ConstraintViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (DiscussionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/discussion_by_user/{idUser}")
    public ResponseEntity<List<DiscussionDTO>> getDiscussionByUser(@PathVariable("idUser") Long idUser) {
        List<DiscussionDTO> discussions = discussionService.getDiscussionByUser(idUser);
        return new ResponseEntity<>(discussions, discussions.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    @GetMapping("/possible_user/{idUser}")
    public List<SimpleUserDTO> getPossibleUser(@PathVariable("idUser") Long idUser){
        return discussionService.findUsersWithoutDiscussionWith(idUser);
    }
    @GetMapping("/users_in_discussion/{idUser}")
    public List<Long> getUsersinDiscussion(@PathVariable("idUser") Long idUser){
        return discussionService.findidUsersIndiscussion(idUser);
    }
}
