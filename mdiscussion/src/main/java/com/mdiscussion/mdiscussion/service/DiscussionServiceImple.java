package com.mdiscussion.mdiscussion.service;

import com.mdiscussion.mdiscussion.dto.DiscussionDTO;
import com.mdiscussion.mdiscussion.dto.MessageDTO;
import com.mdiscussion.mdiscussion.dto.UserDTO;
import com.mdiscussion.mdiscussion.exception.DiscussionException;
import com.mdiscussion.mdiscussion.mapper.DiscussionMapper;
import com.mdiscussion.mdiscussion.model.Discussion;
import com.mdiscussion.mdiscussion.repository.DiscussionRepository;
import com.mdiscussion.mdiscussion.service.client.MessageFeignClient;
import com.mdiscussion.mdiscussion.service.client.UserFeignClient;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class DiscussionServiceImple implements  DiscussionService{
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscussionServiceImple.class);
    private DiscussionRepository discussionRepository;
    private UserFeignClient userFeignClient;
    private MessageFeignClient messageFeignClient;
    @Override
    public DiscussionDTO saveDiscussion(DiscussionDTO discussionDto) {
        Discussion discussion = DiscussionMapper.mapToDiscussion(discussionDto);
        Discussion savedDiscussion = discussionRepository.save(discussion);
        DiscussionDTO savedDiscussionDto = DiscussionMapper.mapToDiscussionDto(savedDiscussion);
        return savedDiscussionDto;
    }

    @Override
    public List<DiscussionDTO> getALLDiscussion() {
        List<Discussion> discussions = discussionRepository.findAll();
        List<DiscussionDTO> discussionsdto= new ArrayList<>();
        if (discussions.size() > 0) {
            for (Discussion discussion : discussions) {
                DiscussionDTO dto = DiscussionMapper.mapToDiscussionDto(discussion);
                discussionsdto.add(dto);
            }
            return discussionsdto;
        }else {
            return new ArrayList<DiscussionDTO>();
        }
    }

    @Override
    public void deleteDiscussion(Long idDiscussion) throws DiscussionException {
        Optional<Discussion> discussionOptional = discussionRepository.findById(idDiscussion);
        if (!discussionOptional.isPresent()) {
            throw new DiscussionException(DiscussionException.NotFoundException(idDiscussion));
        } else {
            discussionRepository.deleteById(idDiscussion);
        }
    }

    @Override
    public DiscussionDTO getDiscussion(Long idDiscussion) throws DiscussionException {
        Optional<Discussion> discussionOptional = discussionRepository.findById(idDiscussion);
        if (!discussionOptional.isPresent()) {
            throw new DiscussionException(DiscussionException.NotFoundException(idDiscussion));
        }else {
            DiscussionDTO dto =DiscussionMapper.mapToDiscussionDto(discussionOptional.get());
            UserDTO user1 = userFeignClient.getUser(dto.getIdUser1()).getBody();
            dto.setUser1(user1);
            UserDTO user2 = userFeignClient.getUser(dto.getIdUser2()).getBody();
            dto.setUser2(user2);
            List<MessageDTO> messages = messageFeignClient.getAllMessageByDiscussion(dto.getIdDiscussion()).getBody();
            dto.setMessages(messages);
            return dto;
        }
    }

    @Override
    public void updateDiscussion(Long idDiscussion, Discussion discussion) throws DiscussionException {
        Optional<Discussion> discussionWithId = discussionRepository.findById(idDiscussion);
        if(discussionWithId.isPresent())
        {
            Discussion discussionToUpdate=discussionWithId.get();
            discussionToUpdate.setIdUser1(discussion.getIdUser1());
            discussionToUpdate.setIdUser2(discussion.getIdUser2());
            discussionToUpdate.setIdGroupe(discussion.getIdGroupe());
            discussionToUpdate.setStatus(discussion.getStatus());
            discussionRepository.save(discussionToUpdate);
        }
        else
        {
            throw new DiscussionException(DiscussionException.NotFoundException(idDiscussion));
        }
    }

    @Override
    public List<DiscussionDTO> getDiscussionByUser(Long idUser) {
        List<Discussion> discussions = discussionRepository.findDiscussionByIdUser1OrIdUser2(idUser,idUser);
        List<DiscussionDTO> discussionsdto= new ArrayList<>();
        if (discussions.size() > 0) {
            for (Discussion discussion : discussions) {
                DiscussionDTO dto = DiscussionMapper.mapToDiscussionDto(discussion);
                discussionsdto.add(dto);
            }
            return discussionsdto;
        }else {
            return new ArrayList<DiscussionDTO>();
        }
    }
}
