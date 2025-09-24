package Repository;

import Model.Player; // Mengimport Player dari model [cite: 27]
import java.util.*;
import java.util.stream.Collectors;

public class PlayerRepository extends BaseRepository<Player, UUID> {

    @Override
    public void save(Player player) { // Meng-override method save [cite: 41]
        UUID id = getId(player);
        dataMap.put(id, player);
        allData.add(player);
    }

    @Override
    public UUID getId(Player entity) { // Meng-override method getId [cite: 42]
        return entity.getPlayerId();
    }

    /**
     * Mencari Player berdasarkan username[cite: 29].
     */
    public Optional<Player> findByUsername(String username) {
        return allData.stream()
                .filter(player -> player.getUsername().equals(username))
                .findFirst();
    }

    /**
     * Mengembalikan daftar Player dengan highscore tertinggi[cite: 36].
     */
    public List<Player> findTopPlayersByHighScore(int limit) {
        return allData.stream()
                .sorted((p1, p2) -> Integer.compare(p2.getHighScore(), p1.getHighScore()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    /**
     * Mengembalikan Player dengan skor lebih besar dari minScore[cite: 37].
     */
    public List<Player> findByHighscoreGreaterThan(int minScore) {
        return allData.stream()
                .filter(player -> player.getHighScore() > minScore)
                .collect(Collectors.toList());
    }

    /**
     * Mengembalikan semua Player diurutkan berdasarkan total koin[cite: 39].
     */
    public List<Player> findAllByOrderByTotalCoinsDesc() {
        return allData.stream()
                .sorted((p1, p2) -> Integer.compare(p2.getTotalCoins(), p1.getTotalCoins()))
                .collect(Collectors.toList());
    }

    /**
     * Mengembalikan semua Player diurutkan berdasarkan total jarak[cite: 40].
     */
    public List<Player> findAllByOrderByTotalDistanceTravelledDesc() {
        return allData.stream()
                .sorted((p1, p2) -> Integer.compare(p2.getTotalDistance(), p1.getTotalDistance()))
                .collect(Collectors.toList());
    }
}