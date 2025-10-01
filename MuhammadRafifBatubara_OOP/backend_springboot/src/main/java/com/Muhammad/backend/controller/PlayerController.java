package com.Muhammad.backend.controller;

import com.Muhammad.backend.model.Player;
import com.Muhammad.backend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/players")
public class PlayerController{

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers(){
        List<Player> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<?> getPlayerById(@PathVariable UUID playerId){
        Optional<Player> player = playerService.getPlayerById(playerId);
        if(player.isPresent()){
            return ResponseEntity.ok(player.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not found with id: " + playerId);
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getPlayerByUsername(@PathVariable String username){
        Optional<Player> player = playerService.getPlayerByUsername(username);
        if(player.isPresent()){
            return ResponseEntity.ok(player.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not found with username: " + username);
        }
    }

    @GetMapping("/check-username/{username}")
    public ResponseEntity<Boolean> checkUsername(@PathVariable String username){
        boolean exists = playerService.isUsernameExists(username);
        return ResponseEntity.ok(exists);
    }

    @PostMapping
    public ResponseEntity<?> createPlayer(@RequestBody Player player){
        try{
            Player createdplayer = playerService.createPlayer(player);
            return new ResponseEntity<>(createdplayer, HttpStatus.CREATED);
        }
        catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{playerId}")
    public ResponseEntity<?> updatePlayer(@PathVariable UUID playerId, @RequestBody Player player){
        try{
            Player updatedplayer = playerService.updatePlayer(playerId, player);
            return  ResponseEntity.ok(updatedplayer);
        }
        catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<?> deletePlayer(@PathVariable UUID playerId){
        try{
            playerService.deletePlayer(playerId);
            return  ResponseEntity.ok("Player with id " + playerId + " has been deleted");
        }
        catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/leaderboard/high-score")
    public  ResponseEntity<List<Player>> getLeaderboardByHighScore(@RequestParam(defaultValue = "10") int limit){
        List<Player> leaderboard = playerService.getLeaderboardByHighScore(limit);
        return ResponseEntity.ok(leaderboard);
    }

    @GetMapping("/leaderboard/total-coins")
    public ResponseEntity<List<Player>> getLeaderboardByTotalCoins(){
        List<Player> leaderboard = playerService.getLeaderboardByTotalCoins();
        return ResponseEntity.ok(leaderboard);
    }

    @GetMapping("/leaderboard/total-distance")
    public ResponseEntity<List<Player>> getLeaderboardByTotalDistance(){
        List<Player> leaderboard = playerService.getLeaderboardByTotalDistance();
        return ResponseEntity.ok(leaderboard);
    }




}