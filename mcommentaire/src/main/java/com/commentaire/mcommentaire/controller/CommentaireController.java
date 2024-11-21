package com.commentaire.mcommentaire.controller;

import com.commentaire.mcommentaire.dto.CommentaireDTO;
import com.commentaire.mcommentaire.exception.CommentaireException;
import com.commentaire.mcommentaire.model.Commentaire;
import com.commentaire.mcommentaire.service.CommentaireService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("commentaire")
@AllArgsConstructor
public class CommentaireController {


    private CommentaireService commentaireService;
    @GetMapping(value = "/health/readiness")
    public ResponseEntity<String> test(){
        return ResponseEntity.status(HttpStatus.OK).body("hi");
    }
    @PostMapping
    public CommentaireDTO saveCommentaire(@RequestBody CommentaireDTO commentaireDto){
        CommentaireDTO savedCommentaire = commentaireService.saveCommentaire(commentaireDto);
        return savedCommentaire;
    }
    @GetMapping("")
    public List<CommentaireDTO> getAllCommentaire() {
        List<CommentaireDTO> commentaires = commentaireService.getALLCommentaire();
        return commentaires ;
    }

    @GetMapping("/{idCommentaire}")
    public CommentaireDTO getCommentaire(@PathVariable("idCommentaire") Long idCommentaire) throws CommentaireException {
        try {
          return  commentaireService.getCommentaire(idCommentaire);
        } catch (CommentaireException e) {
          return new CommentaireDTO();
        }
    }

    @DeleteMapping("/{idCommentaire}")
    public ResponseEntity<?> deleteById(@PathVariable("idCommentaire") Long idCommentaire) throws CommentaireException{
        try{
            commentaireService.deleteCommentaire(idCommentaire);
            return new ResponseEntity<>("Successfully deleted commentaire with id "+idCommentaire, HttpStatus.OK);
        }
        catch (CommentaireException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idCommentaire}")
    public ResponseEntity<?> updateById(@PathVariable("idCommentaire") Long idCommentaire, @RequestBody Commentaire commentaire)
    {
        try {
            commentaireService.updateCommentaire(idCommentaire,commentaire);
            return new ResponseEntity<>("Updated commentaire with id "+idCommentaire+"", HttpStatus.OK);
        }
        catch(ConstraintViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (CommentaireException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/comment_by_forum/{idForum}")
    public List<CommentaireDTO> getcommentByForum(@PathVariable("idForum") Long idForum) {
        List<CommentaireDTO> commentaires = commentaireService.getCommentByForum(idForum);
        return commentaires;
    }
}
