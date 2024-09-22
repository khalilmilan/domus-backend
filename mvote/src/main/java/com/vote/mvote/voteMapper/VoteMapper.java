package com.vote.mvote.voteMapper;

import com.vote.mvote.dto.VoteDTO;
import com.vote.mvote.model.Vote;

public class VoteMapper {

    public static VoteDTO mapToVoteDto(Vote vote){
        VoteDTO voteDto = new VoteDTO(
                vote.getIdVote(),
                vote.getTitle(),
                vote.getStatus(),
                vote.getIdUser(),
                vote.getIdSurvey(),
                vote.getIdSurveyValue()
        );
        return voteDto;
    }

    public static Vote mapToVote(VoteDTO eventDto){
        Vote vote = new Vote(
                eventDto.getIdVote(),
                eventDto.getTitle(),
                eventDto.getStatus(),
                eventDto.getIdUser(),
                eventDto.getIdSurvey(),
                eventDto.getIdSurveyValue()
        );
        return vote;
    }
}
