package com.mdiscussion.mdiscussion.mapper;

import com.mdiscussion.mdiscussion.dto.DiscussionDTO;
import com.mdiscussion.mdiscussion.dto.MessageDTO;
import com.mdiscussion.mdiscussion.dto.SimpleUserDTO;
import com.mdiscussion.mdiscussion.model.Discussion;

import java.util.ArrayList;

public class DiscussionMapper {

    public static DiscussionDTO mapToDiscussionDto(Discussion discussion){
        DiscussionDTO discussionDto = new DiscussionDTO(
                discussion.getIdDiscussion(),
                discussion.getIdUser1(),
                discussion.getIdUser2(),
                discussion.getIdFireBase(),
                discussion.getIdGroupe(),
                discussion.getStatus(),
                new MessageDTO(),
                new SimpleUserDTO(),
                new SimpleUserDTO(),
                new ArrayList<>(),
                0L
        );
        return discussionDto;
    }

    public static Discussion mapToDiscussion(DiscussionDTO discussionDto ){
        Discussion discussion = new Discussion(
                discussionDto.getIdDiscussion(),
                discussionDto.getIdUser1(),
                discussionDto.getIdUser2(),
                discussionDto.getIdFireBase(),
                discussionDto.getIdGroupe(),
                discussionDto.getStatus()
        );
        return discussion;
    }
}
