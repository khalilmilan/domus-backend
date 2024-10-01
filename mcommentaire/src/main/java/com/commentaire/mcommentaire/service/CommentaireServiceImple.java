package com.commentaire.mcommentaire.service;

import com.commentaire.mcommentaire.client.UserServiceClient;
import com.commentaire.mcommentaire.commentaireMapper.CommentaireMapper;
import com.commentaire.mcommentaire.dto.CommentaireDTO;
import com.commentaire.mcommentaire.dto.SimpleUserDTO;
import com.commentaire.mcommentaire.exception.CommentaireException;
import com.commentaire.mcommentaire.model.Commentaire;
import com.commentaire.mcommentaire.repository.CommentaireRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentaireServiceImple implements CommentaireService{

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentaireServiceImple.class);

    private CommentaireRepository commentaireRepository;
    private UserServiceClient userFeignClient;
    @Override
    public CommentaireDTO saveCommentaire(CommentaireDTO commentaireDto) {
        Commentaire commentaire = CommentaireMapper.mapToCommentaire(commentaireDto);
        Commentaire savedCommentaire = commentaireRepository.save(commentaire);
        CommentaireDTO savedCommentaireDto = CommentaireMapper.mapToCommentaireDto(savedCommentaire);
        return savedCommentaireDto;
    }

    @Override
    public List<CommentaireDTO> getALLCommentaire() {
        List<Commentaire> commentaires = commentaireRepository.findAll();
        List<CommentaireDTO> commentaireDto= new ArrayList<>();
        if (commentaires.size() > 0) {
            for (Commentaire commentaire : commentaires) {
                CommentaireDTO dto = CommentaireMapper.mapToCommentaireDto(commentaire);
                commentaireDto.add(dto);
            }
            return commentaireDto;
        }else {
            return new ArrayList<CommentaireDTO>();
        }
    }

    @Override
    public void deleteCommentaire(Long idCommentaire) throws CommentaireException {
        Optional<Commentaire> commentaireOptional = commentaireRepository.findById(idCommentaire);
        if (!commentaireOptional.isPresent()) {
            throw new CommentaireException(CommentaireException.NotFoundException(idCommentaire));
        } else {
            commentaireRepository.deleteById(idCommentaire);
        }
    }

    @Override
    public CommentaireDTO getCommentaire(Long idCommentaire) throws CommentaireException {
        Optional<Commentaire> commentaireOptional = commentaireRepository.findById(idCommentaire);
        if (!commentaireOptional.isPresent()) {
            throw new CommentaireException(CommentaireException.NotFoundException(idCommentaire));
        }else {
            CommentaireDTO dto = CommentaireMapper.mapToCommentaireDto(commentaireOptional.get());
            SimpleUserDTO user = userFeignClient.getSimpleUser(dto.getIdUser());
            dto.setUser(user);
            return dto;
        }
    }

    @Override
    public void updateCommentaire(Long idCommentaire, Commentaire commentaire) throws CommentaireException {
        Optional<Commentaire> commentaireWithId = commentaireRepository.findById(idCommentaire);
        if(commentaireWithId.isPresent())
        {
            Commentaire commentaireToUpdate=commentaireWithId.get();
            commentaireToUpdate.setContent(commentaire.getContent());
            commentaireRepository.save(commentaireToUpdate);
        }
        else
        {
            throw new CommentaireException(CommentaireException.NotFoundException(idCommentaire));
        }
    }

    @Override
    public List<CommentaireDTO> getCommentByForum(Long idForum) {
        List<Commentaire> commentaires = commentaireRepository.findCommetaireByIdForum(idForum);
        List<CommentaireDTO> commentaireDto= new ArrayList<>();
        if (commentaires.size() > 0) {
            for (Commentaire commentaire : commentaires) {
                CommentaireDTO dto = CommentaireMapper.mapToCommentaireDto(commentaire);
                SimpleUserDTO user = userFeignClient.getSimpleUser(dto.getIdUser());
                dto.setUser(user);
                commentaireDto.add(dto);
            }
            return commentaireDto;
        }else {
            return new ArrayList<CommentaireDTO>();
        }
    }
}
