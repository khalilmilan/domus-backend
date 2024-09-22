package com.forum.mforum.service.client;

import com.forum.mforum.dto.CommentaireDTO;
import com.forum.mforum.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="microservice-commentaire", fallback = CommentaireFallback.class)
public interface CommentaireFeignClient {

    @GetMapping(value = "/commentaire/comment_by_forum/{idForum}",consumes = "application/json")
     ResponseEntity<List<CommentaireDTO>> getcommentByForum(@PathVariable("idForum") Long idForum);

}
