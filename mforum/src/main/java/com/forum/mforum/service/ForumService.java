package com.forum.mforum.service;

import com.forum.mforum.dto.ForumDTO;
import com.forum.mforum.exception.ForumException;
import com.forum.mforum.model.Forum;

import java.util.List;

public interface ForumService {

    ForumDTO saveForum(ForumDTO forumDto);

    List<ForumDTO> getALLForum();

    void deleteForum(Long idForum) throws ForumException;

    ForumDTO getForum(Long idForum) throws ForumException;

     void updateForum(Long idForum, Forum forum) throws ForumException;
     List<ForumDTO> getForumsByEvent(Long idEvent);
}
