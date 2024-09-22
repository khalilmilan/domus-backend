package com.vote.mvote.repository;

import com.vote.mvote.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote,Long> {
    List<Vote> findVotesByIdUser(Long idUser);
    List<Vote> findVotesByIdSurvey(Long idSurvey);
    List<Vote> findVoteByIdSurveyAndIdSurveyValue(Long idSurvey,Long idSurveyValue);

}
