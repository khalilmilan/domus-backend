package com.forum.mforum.service.client;

import com.forum.mforum.dto.CommentaireDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CommentaireFallback implements CommentaireFeignClient{
    @Override
    public ResponseEntity<List<CommentaireDTO>> getcommentByForum(Long idForum) {
        return null;
    }
}
