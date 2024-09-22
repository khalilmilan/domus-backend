package com.vote.mvote.service;

import com.vote.mvote.dto.VoteDTO;
import com.vote.mvote.exception.VoteException;
import com.vote.mvote.model.Vote;

import java.util.List;

public interface VoteService {
    VoteDTO saveVote(VoteDTO voteDto);

    List<VoteDTO> getALLVote();

    void deleteVote(Long idVote) throws VoteException;

    VoteDTO getVote(Long idVote) throws VoteException;

     void updateVote(Long idVote, Vote vote) throws VoteException;

    List<VoteDTO> getVoteByUser(Long idUser);
    List<VoteDTO> getVoteBySurvey(Long idSurvey);
    List<VoteDTO> getVoteBySurveyAndSurveyValue(Long idSurvey, Long idSurveyValue);
}
