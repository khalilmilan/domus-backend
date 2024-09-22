package com.rank.mrank.controller;

import com.rank.mrank.dto.RankDTO;
import com.rank.mrank.exception.RankException;
import com.rank.mrank.model.Rank;
import com.rank.mrank.service.RankService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("rank")
@AllArgsConstructor
public class RankController {


    private RankService rankService;
    @GetMapping(value = "/lowel")
    public String test(){
        return "hi";
    }
    @PostMapping
    public ResponseEntity<RankDTO> saveRank(@RequestBody RankDTO rankDTO){
        RankDTO savedRank = rankService.saveRank(rankDTO);
        return new ResponseEntity<>(savedRank, HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllRank() {
        List<RankDTO> ranks = rankService.getALLRank();
        return new ResponseEntity<>(ranks, ranks.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{idRank}")
    public ResponseEntity<?> getRank(@PathVariable("idRank") Long idRank) throws RankException {
        try {
            return new ResponseEntity<>(rankService.getRank(idRank), HttpStatus.OK);
        } catch (RankException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idRank}")
    public ResponseEntity<?> deleteById(@PathVariable("idRank") Long idRank) throws RankException{
        try{
            rankService.deleteRank(idRank);
            return new ResponseEntity<>("Successfully deleted rank with id "+idRank, HttpStatus.OK);
        }
        catch (RankException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idRank}")
    public ResponseEntity<?> updateById(@PathVariable("idRank") Long idRank, @RequestBody Rank rank)
    {
        try {
            rankService.updateRank(idRank,rank);
            return new ResponseEntity<>("Updated rank with id "+idRank+"", HttpStatus.OK);
        }
        catch(ConstraintViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (RankException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
