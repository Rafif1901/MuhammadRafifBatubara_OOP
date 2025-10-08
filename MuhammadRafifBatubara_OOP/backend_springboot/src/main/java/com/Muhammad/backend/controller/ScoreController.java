package com.Muhammad.backend.controller;

import com.Muhammad.backend.model.Score;
import com.Muhammad.backend.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
            return ResponseEntity.ok(score.get());
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
        return ResponseEntity.ok(scoreService.getScoresByPlayerId(playerId));
    }

    @GetMapping("/player/{playerId}/ordered")
    public ResponseEntity<List<Score>> getScoresByPlayerIdOrdered(@PathVariable UUID playerId){
        return ResponseEntity.ok(scoreService.getScoresByPlayerIdOrderByValue(playerId));
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<List<Score>> getLeaderboard(@RequestParam(defaultValue = "10")int limit){
        List<Score> leaderboard = scoreService.getLeaderboard(limit);
        return ResponseEntity.ok(leaderboard);
    }

    @GetMapping("/player/{playerId}/highest")
    public ResponseEntity<?> getHighestScoreByPlayerId(@PathVariable UUID playerId){
        Optional<Score> highest = scoreService.getHighestScoreByPlayerId(playerId);
        if(highest.isPresent()){
            return ResponseEntity.ok(highest.get());
            }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No scores found..");
        }
    }

    @GetMapping("/above/{minValue}")
    public ResponseEntity<List<Score>> getScoresAboveValue(@PathVariable Integer minValue){
        return ResponseEntity.ok(scoreService.getScoresAboveValue(minValue));
    }

    @GetMapping("/recent")
    public ResponseEntity<List<Score>> getRecentScores(){
        return ResponseEntity.ok(scoreService.getRecentScores());
    }

    @GetMapping("/player/{playerId}/total-coins")
    public ResponseEntity<?> getTotalCoinsByPlayerId(@PathVariable UUID playerId){
        Map<String, Object> response = new HashMap<>();
        response.put("totalCoins", scoreService.getTotalCoinsByPlayerId(playerId));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/player/{playerId}/total-distance")
    public ResponseEntity<?> getTotalDistanceByPlayerId(@PathVariable UUID playerId){
        Map<String, Object> response = new HashMap<>();
        response.put("totalDistance", scoreService.getTotalDistanceByPlayerId(playerId));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/player/{playerId}")
    public ResponseEntity<?> deleteScoresByPlayerId(@PathVariable UUID playerId){
        scoreService.deleteScoresByPlayerId(playerId);
        return ResponseEntity.ok("All Scores deleted successfully");
    }
}