package com.forum.mforum.client;

import com.forum.mforum.configurations.EventFeignClientConfig;
import com.forum.mforum.dto.CommentaireDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="microservice-commentaire", configuration = EventFeignClientConfig.class)
public interface CommentaireFeignClient {

    @GetMapping(value = "/commentaire/comment_by_forum/{idForum}",consumes = "application/json")
     List<CommentaireDTO> getcommentByForum(@PathVariable("idForum") Long idForum);

}
