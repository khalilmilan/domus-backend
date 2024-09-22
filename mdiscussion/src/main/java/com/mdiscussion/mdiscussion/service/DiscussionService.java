package com.mdiscussion.mdiscussion.service;

import com.mdiscussion.mdiscussion.dto.DiscussionDTO;
import com.mdiscussion.mdiscussion.exception.DiscussionException;
import com.mdiscussion.mdiscussion.model.Discussion;

import java.util.List;

public interface DiscussionService {

    DiscussionDTO saveDiscussion(DiscussionDTO discussionDto);

    List<DiscussionDTO> getALLDiscussion();

    void deleteDiscussion(Long idDiscussion) throws DiscussionException;

    DiscussionDTO getDiscussion(Long idDiscussion) throws DiscussionException;

    void updateDiscussion(Long idDiscussion, Discussion discussion) throws DiscussionException;

    List<DiscussionDTO> getDiscussionByUser(Long idUser1);
}
