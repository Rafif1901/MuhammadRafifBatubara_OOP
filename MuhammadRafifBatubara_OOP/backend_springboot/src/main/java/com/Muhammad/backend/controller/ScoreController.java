package com.Muhammad.backend.controller;

import com.Muhammad.backend.model.Score;
import com.Muhammad.backend.repository.ScoreRepository;
import com.Muhammad.backend.service.ScoreService;
import com.Muhammad.backend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/scores")
@CrossOrigin(origins = "*")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @PostMapping
    public ResponseEntity<?> createScore(@RequestBody Score score){
        try{
            Score newScore = scoreService.createScore(score);
            return ResponseEntity.status(HttpStatus.CREATED).body(newScore);
        }
        catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Score>> getAllScores(){
        return ResponseEntity.ok(scoreService.getAllScores());
    }

    @GetMapping("/{scoreId}")
    public ResponseEntity<?> getScoreById(@PathVariable UUID scoreId){
        Optional<Score> score =  scoreService.getScoreById(scoreId);
        if(score.isPresent()){
            return ResponseEntity.ok(score);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Score not found with id: " + scoreId);
        }
    }

    @PutMapping("/{scoreId}")
    public ResponseEntity<?> updateScore(@PathVariable UUID scoreId, @RequestBody Score score){
        try{
            Score updated = scoreService.updateScore(scoreId, score);
            return ResponseEntity.ok(updated);
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{scoreId}")
    public ResponseEntity<?> deleteScore(@PathVariable UUID scoreId){
        try{
            scoreService.deleteScore(scoreId);
            return ResponseEntity.ok("Score deleted successfully");
        }
        catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<Score>> getScoresByPlayerId(@PathVariable UUID playerId){
        return ResponseEntity.ok(scoreService.getHighestScoreByPlayerId(playerId));
    }


}