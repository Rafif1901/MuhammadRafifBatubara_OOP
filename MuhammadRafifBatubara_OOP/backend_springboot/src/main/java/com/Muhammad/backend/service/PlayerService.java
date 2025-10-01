package com.Muhammad.backend.service;

import com.Muhammad.backend.model.Player;
import com.Muhammad.backend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public boolean isUsernameExists(String username) {
        return playerRepository.existsByUsername(username);
    }

    public Player createPlayer(Player player) {
        if (playerRepository.existsByUsername(player.getUsername())) {
            throw new RuntimeException("Username already exists: " + player.getUsername());
        }
        return playerRepository.save(player);
    }

    public Player updatePlayer(UUID playerId, Player updatedPlayer) {
        Player existingPlayer = getPlayerById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found with id: " + playerId));

        if(updatedPlayer.getHighscore() != null){
            existingPlayer.setHighscore(updatedPlayer.getHighscore());
        }
        if(updatedPlayer.getTotalCoins() != null){
            existingPlayer.setTotalCoins(updatedPlayer.getTotalCoins());
        }
        if(updatedPlayer.getTotalDistance() != null){
            existingPlayer.setTotalDistance(updatedPlayer.getTotalDistance());
        }
        return playerRepository.save(existingPlayer);
    }

    public void deletePlayer(UUID playerId) {
        if(!playerRepository.existsById(playerId)){
            throw new RuntimeException("Player not found with id: " + playerId);
        }
        playerRepository.deleteById(playerId);
    }

    public Player updatePlayerStats(UUID playerId, Integer scoreValue, Integer coinsCollected, Integer distanceTravelled) {
        Player player = getPlayerById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found!"));

        if(scoreValue != null){
            player.updateHighScore(scoreValue);
        }
        if(coinsCollected != null){
            player.addCoins(coinsCollected);
        }
        if(distanceTravelled != null){
            player.addDistance(distanceTravelled);
        }
        return playerRepository.save(player);
    }

    public Optional<Player> getPlayerById(UUID playerId) {
        return playerRepository.findById(playerId);
    }

    public Optional<Player> getPlayerByUsername(String username) {
        return playerRepository.findByUsername(username);
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public List<Player> getLeaderboardByHighScore(int limit) {
        return playerRepository.findTopPlayerByHighScore(limit);
    }

    public List<Player> getLeaderboardByTotalCoins() {
        return playerRepository.findAllByOrderByTotalCoinsDesc();
    }

    public List<Player> getLeaderboardByTotalDistance() {
        return playerRepository.findByOrderByTotalDistanceDesc();
    }
}

