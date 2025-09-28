package Service;

import Model.Player;
import Repository.PlayerRepository;

import java.util.*;

/**
 * PlayerService bertanggung jawab untuk mengelola logika bisnis terkait Player.
 * Menggunakan PlayerRepository sebagai data access layer.
 */
public class PlayerService {

    // Attribute repository (hanya bisa diakses dari dalam class)
    private final PlayerRepository playerRepository;

    /**
     * Konstruktor PlayerService untuk inisialisasi repository.
     *
     * @param playerRepository repository untuk player
     */
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    /**
     * Mengecek apakah username sudah terdaftar.
     *
     * @param username username player
     * @return true jika sudah ada, false jika belum
     */
    public boolean existsByUsername(String username) {
        return playerRepository.existByUsername(username);
    }

    /**
     * Membuat player baru.
     *
     * @param player objek Player
     * @return player yang berhasil disimpan
     * @throws RuntimeException jika username sudah terdaftar
     */
    public Player createPlayer(Player player) {
        if (existsByUsername(player.getUsername())) {
            throw new RuntimeException("Username sudah terdaftar!");
        }
        playerRepository.save(player);
        return player;
    }

    /**
     * Mendapatkan player berdasarkan id.
     *
     * @param playerId UUID player
     * @return optional Player
     */
    public Optional<Player> getPlayerById(UUID playerId) {
        return playerRepository.findById(playerId);
    }


    /**
     * Mendapatkan player berdasarkan username.
     *
     * @param username username player
     * @return optional Player
     */
    public Optional<Player> getPlayerByUsername(String username) {
        return playerRepository.findByUsername(username);
    }

    /**
     * Mendapatkan semua player.
     *
     * @return daftar player
     */
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    /**
     * Update data player.
     *
     * @param playerId      id player yang mau diupdate
     * @param updatedPlayer data baru
     * @return player yang sudah diperbarui
     * @throws RuntimeException jika player tidak ditemukan atau username sudah dipakai
     */
    public Player updatePlayer(UUID playerId, Player updatedPlayer) {
        Player existingPlayer = getPlayerById(playerId)
                .orElseThrow(() -> new RuntimeException("Player tidak ditemukan!"));

        // Cek jika username baru sudah dipakai oleh player lain
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

    /**
     * Menghapus player berdasarkan id.
     *
     * @param playerId UUID player
     */
    public void deletePlayer(UUID playerId) {
        playerRepository.deleteById(playerId);
    }

    /**
     * Menghapus player berdasarkan username.
     *
     * @param username username player
     */
    public void deletePlayerByUsername(String username) {
        playerRepository.findByUsername(username)
                .ifPresent(player -> playerRepository.deleteById(player.getPlayerId()));
    }

    /**
     * Update statistik player.
     *
     * @param playerId          id player
     * @param scoreValue        nilai score
     * @param coinsCollected    jumlah coin
     * @param distanceTravelled jarak
     * @return player setelah diperbarui
     */
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

    /**
     * Leaderboard berdasarkan highscore.
     *
     * @param limit batas jumlah player
     * @return daftar player dengan highscore tertinggi
     */
    public List<Player> getLeaderboardByHighScore(int limit) {
        return playerRepository.findTopPlayersByHighScore(limit);
    }

    /**
     * Leaderboard berdasarkan total coins.
     *
     * @return daftar player terurut coins desc
     */
    public List<Player> getLeaderboardByTotalCoins() {
        return playerRepository.findAllByOrderByTotalCoinsDesc();
    }

    /**
     * Leaderboard berdasarkan total distance.
     *
     * @return daftar player terurut distance desc
     */
    public List<Player> getLeaderboardByTotalDistance() {
        return playerRepository.findAllByorderByTotalDistanceTravelledDesc();
    }
}
