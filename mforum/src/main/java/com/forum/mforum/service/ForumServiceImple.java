package com.forum.mforum.service;

import com.forum.mforum.dto.CommentaireDTO;
import com.forum.mforum.dto.EventDTO;
import com.forum.mforum.dto.ForumDTO;
import com.forum.mforum.dto.UserDTO;
import com.forum.mforum.exception.ForumException;
import com.forum.mforum.forumMapper.ForumMapper;
import com.forum.mforum.model.Forum;
import com.forum.mforum.repository.ForumRepository;
import com.forum.mforum.service.client.CommentaireFeignClient;
import com.forum.mforum.service.client.EventFeignClient;
import com.forum.mforum.service.client.UserFeignClient;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ForumServiceImple implements ForumService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ForumServiceImple.class);

    private ForumRepository forumRepository;
    private UserFeignClient userFeignClient;
    private EventFeignClient eventFeignClient;
    private CommentaireFeignClient commentaireFeignClient;
    @Override
    public ForumDTO saveForum(ForumDTO forumDto) {
        Forum forum = ForumMapper.mapToForum(forumDto);
        Forum savedForum = forumRepository.save(forum);
        ForumDTO savedForumDto = ForumMapper.mapToForumDto(savedForum);
        return savedForumDto;
    }

    @Override
    public List<ForumDTO> getALLForum() {
        List<Forum> forums = forumRepository.findAll();
        List<ForumDTO> forumDto= new ArrayList<>();
        if (forums.size() > 0) {
            for (Forum forum : forums) {
                ForumDTO dto =ForumMapper.mapToForumDto(forum);
                EventDTO event = eventFeignClient.getEvent(dto.getIdEvent()).getBody();
                dto.setEvent(event);
                UserDTO user = userFeignClient.getUser(dto.getIdUser()).getBody();
                dto.setUser(user);
                forumDto.add(dto);
            }
            return forumDto;
        }else {
            return new ArrayList<ForumDTO>();
        }
    }

    @Override
    public void deleteForum(Long idForum) throws ForumException {
        Optional<Forum> forumOptional = forumRepository.findById(idForum);
        if (!forumOptional.isPresent()) {
            throw new ForumException(ForumException.NotFoundException(idForum));
        } else {
            forumRepository.deleteById(idForum);
        }
    }

    @Override
    public ForumDTO getForum(Long idForum) throws ForumException {
        Optional<Forum> forumOptional = forumRepository.findById(idForum);
        if (!forumOptional.isPresent()) {
            throw new ForumException(ForumException.NotFoundException(idForum));
        }else {
            ForumDTO dto =ForumMapper.mapToForumDto(forumOptional.get());
            EventDTO event = eventFeignClient.getEvent(dto.getIdEvent()).getBody();
            dto.setEvent(event);
            UserDTO user = userFeignClient.getUser(dto.getIdUser()).getBody();
            dto.setUser(user);
            List<CommentaireDTO> comments = commentaireFeignClient.getcommentByForum(dto.getIdForum()).getBody();
            dto.setComments(comments);
            return dto;
        }
    }

    @Override
    public void updateForum(Long idForum, Forum forum) throws ForumException {
        Optional<Forum> forumtWithId = forumRepository.findById(idForum);
        if(forumtWithId.isPresent())
        {
            Forum forumToUpdate=forumtWithId.get();
            forumToUpdate.setTitle(forum.getTitle());
            forumToUpdate.setDescription(forum.getDescription());
            forumToUpdate.setStatus(forum.getStatus());
            forumToUpdate.setIdUser(forum.getIdUser());
            forumToUpdate.setIdEvent(forum.getIdEvent());
            forumRepository.save(forumToUpdate);
        }
        else
        {
            throw new ForumException(ForumException.NotFoundException(idForum));
        }
    }

    @Override
    public List<ForumDTO> getForumsByEvent(Long idEvent) {
        List<Forum> forums = forumRepository.findForymByIdEvent(idEvent);
        List<ForumDTO> forumDto= new ArrayList<>();
        if (forums.size() > 0) {
            for (Forum forum : forums) {
                ForumDTO dto =ForumMapper.mapToForumDto(forum);
                forumDto.add(dto);
            }
            return forumDto;
        }else {
            return new ArrayList<ForumDTO>();
        }
    }
}
