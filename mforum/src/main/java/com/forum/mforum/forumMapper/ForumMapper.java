package com.forum.mforum.forumMapper;

import com.forum.mforum.dto.EventDTO;
import com.forum.mforum.dto.ForumDTO;
import com.forum.mforum.dto.UserDTO;
import com.forum.mforum.model.Forum;

import java.util.ArrayList;

public class ForumMapper {

    public static ForumDTO mapToForumDto(Forum forum){
        ForumDTO forumDto = new ForumDTO(
                forum.getIdForum(),
                forum.getTitle(),
                forum.getDescription(),
                forum.getStatus(),
                forum.getIdUser(),
                forum.getIdEvent(),
                new UserDTO(),
                new EventDTO(),
                new ArrayList<>()

        );
        return forumDto;
    }

    public static Forum mapToForum(ForumDTO forumDto){
        Forum forum = new Forum(
                forumDto.getIdForum(),
                forumDto.getTitle(),
                forumDto.getDescription(),
                forumDto.getStatus(),
                forumDto.getIdUser(),
                forumDto.getIdEvent()
        );
        return forum;
    }
}
