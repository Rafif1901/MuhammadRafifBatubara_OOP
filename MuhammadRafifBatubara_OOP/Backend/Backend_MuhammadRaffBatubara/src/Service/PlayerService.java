package Service;

import Model.Player;
import Repository.PlayerRepository;

import java.util.*;

public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public boolean existsByUsername(String username) {
        return playerRepository.existByUsername(username);
    }

    public Player createPlayer(Player player) {
        if (existsByUsername(player.getUsername())) {
            throw new RuntimeException("Username sudah terdaftar!");
        }
        playerRepository.save(player);
        return player;
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

    public Player updatePlayer(UUID playerId, Player updatedPlayer) {
        Player existingPlayer = getPlayerById(playerId)
                .orElseThrow(() -> new RuntimeException("Player tidak ditemukan!"));

        if (!existingPlayer.getUsername().equals(updatedPlayer.getUsername())
                && existsByUsername(updatedPlayer.getUsername())) {
            throw new RuntimeException("Username sudah digunakan!");
        }

        existingPlayer.setUsername(updatedPlayer.getUsername());
        existingPlayer.setHighScore(updatedPlayer.getHighScore());
        existingPlayer.setTotalCoins(updatedPlayer.getTotalCoins());
        existingPlayer.setTotalDistance(updatedPlayer.getTotalDistance());

        return existingPlayer;
    }

    public void deletePlayer(UUID playerId) {
        playerRepository.deleteById(playerId);
    }

    public void deletePlayerByUsername(String username) {
        playerRepository.findByUsername(username)
                .ifPresent(player -> playerRepository.deleteById(player.getPlayerId()));
    }

    public Player updatePlayerStats(UUID playerId, int scoreValue, int coinsCollected, int distanceTravelled) {
        Player player = getPlayerById(playerId)
                .orElseThrow(() -> new RuntimeException("Player tidak ditemukan!"));

        if (scoreValue > player.getHighScore()) {
            player.setHighScore(scoreValue);
        }
        player.setTotalCoins(player.getTotalCoins() + coinsCollected);
        player.setTotalDistance(player.getTotalDistance() + distanceTravelled);

        return player;
    }

    public List<Player> getLeaderboardByHighScore(int limit) {
        return playerRepository.findTopPlayersByHighScore(limit);
    }

    public List<Player> getLeaderboardByTotalCoins() {
        return playerRepository.findAllByOrderByTotalCoinsDesc();
    }

    public List<Player> getLeaderboardByTotalDistance() {
        return playerRepository.findAllByorderByTotalDistanceTravelledDesc();
    }
}
