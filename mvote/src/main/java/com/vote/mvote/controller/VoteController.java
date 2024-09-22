package com.vote.mvote.controller;

import com.vote.mvote.dto.VoteDTO;
import com.vote.mvote.exception.VoteException;
import com.vote.mvote.model.Vote;
import com.vote.mvote.service.VoteService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("vote")
@AllArgsConstructor
public class VoteController {

    private VoteService voteService;
    @GetMapping(value = "/lowel")
    public String test(){
        return "hi";
    }
    @PostMapping
    public ResponseEntity<VoteDTO> saveVote(@RequestBody VoteDTO voteDto){
        VoteDTO savedVote = voteService.saveVote(voteDto);
        return new ResponseEntity<>(savedVote, HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllVote() {
        List<VoteDTO> votes = voteService.getALLVote();
        return new ResponseEntity<>(votes, votes.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{idVote}")
    public ResponseEntity<?> getVote(@PathVariable("idVote") Long idVote) throws VoteException {
        try {
            return new ResponseEntity<>(voteService.getVote(idVote), HttpStatus.OK);
        } catch (VoteException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idVote}")
    public ResponseEntity<?> deleteById(@PathVariable("idVote") Long idVote) throws VoteException{
        try{
            voteService.deleteVote(idVote);
            return new ResponseEntity<>("Successfully deleted vote with id "+idVote, HttpStatus.OK);
        }
        catch (VoteException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idVote}")
    public ResponseEntity<?> updateById(@PathVariable("idVote") Long idVote, @RequestBody Vote vote)
    {
        try {
            voteService.updateVote(idVote,vote);
            return new ResponseEntity<>("Updated vote with id "+idVote+"", HttpStatus.OK);
        }
        catch(ConstraintViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (VoteException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/by_user/{idUser}")
    public ResponseEntity<List<VoteDTO>> getVoteByUser(@PathVariable("idUser") Long idUser) {
        List<VoteDTO> votes = voteService.getVoteByUser(idUser);
        if(votes.size() > 0){
            return new ResponseEntity<>(votes,HttpStatus.OK );
        }else{
            return new ResponseEntity<>(new ArrayList<VoteDTO>(), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/by_survey/{idSurvey}")
    public ResponseEntity<List<VoteDTO>> getVoteBySurvey(@PathVariable("idSurvey") Long idSurvey) {
        List<VoteDTO> votes = voteService.getVoteBySurvey(idSurvey);
        if(votes.size() > 0){
            return new ResponseEntity<>(votes,HttpStatus.OK );
        }else{
            return new ResponseEntity<>(new ArrayList<VoteDTO>(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/by_survey_and_survey_value/{idSurvey}/{idSurveyValue}")
    public ResponseEntity<List<VoteDTO>> getVoteBySurveyAndSurveyValue(@PathVariable("idSurvey") Long idSurvey, @PathVariable("idSurveyValue") Long idSurveyValue) {
        List<VoteDTO> votes = voteService.getVoteBySurveyAndSurveyValue(idSurvey,idSurveyValue);
        if(votes.size() > 0){
            return new ResponseEntity<>(votes,HttpStatus.OK );
        }else{
            return new ResponseEntity<>(new ArrayList<VoteDTO>(), HttpStatus.NOT_FOUND);
        }
    }

}
