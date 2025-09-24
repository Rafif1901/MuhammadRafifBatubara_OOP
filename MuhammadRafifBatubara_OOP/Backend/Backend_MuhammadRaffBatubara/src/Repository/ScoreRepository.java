package Repository;

import Model.Score; // Mengimport Score dari model [cite: 45]
import java.util.*;
import java.util.stream.Collectors;

public class ScoreRepository extends BaseRepository<Score, UUID> {

    @Override
    public void save(Score score) { // Meng-override method save [cite: 68]
        UUID id = getId(score);
        dataMap.put(id, score);
        allData.add(score);
    }

    @Override
    public UUID getId(Score entity) { // Meng-override method getId [cite: 69]
        return entity.getScoreId();
    }

    /**
     * Mengembalikan semua skor milik seorang pemain[cite: 47].
     */
    public List<Score> findByPlayerId(UUID playerId) {
        return allData.stream()
                .filter(score -> score.getPlayerId().equals(playerId))
                .collect(Collectors.toList());
    }

    /**
     * Mengembalikan semua skor pemain, diurutkan dari terbesar ke terkecil[cite: 49, 50].
     */
    public List<Score> findByPlayerIdOrderByValueDesc(UUID playerId) {
        return allData.stream()
                .filter(score -> score.getPlayerId().equals(playerId))
                .sorted((s1, s2) -> Integer.compare(s2.getValue(), s1.getValue()))
                .collect(Collectors.toList());
    }

    /**
     * Mengembalikan daftar skor tertinggi secara keseluruhan[cite: 54].
     */
    public List<Score> findTopScores(int limit) {
        return allData.stream()
                .sorted((s1, s2) -> Integer.compare(s2.getValue(), s1.getValue()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    /**
     * Mengembalikan skor tertinggi yang diraih seorang pemain[cite: 55].
     */
    public Optional<Score> findHighestScoreByPlayerId(UUID playerId) {
        return allData.stream()
                .filter(score -> score.getPlayerId().equals(playerId))
                .max(Comparator.comparing(Score::getValue));
    }

    /**
     * Mengembalikan skor yang nilainya lebih tinggi dari minValue[cite: 56].
     */
    public List<Score> findByValueGreaterThan(Integer minValue) {
        return allData.stream()
                .filter(score -> score.getValue() > minValue)
                .collect(Collectors.toList());
    }

    /**
     * Mengembalikan semua skor berdasarkan tanggal dibuat (terbaru dulu)[cite: 57].
     */
    public List<Score> findAllByOrderByCreatedAtDesc() {
        return allData.stream()
                .sorted((s1, s2) -> s2.getCreatedAt().compareTo(s1.getCreatedAt()))
                .collect(Collectors.toList());
    }

    /**
     * Mengembalikan total koin yang dimiliki seorang pemain[cite: 58].
     */
    public Integer getTotalCoinsByPlayerId(UUID playerId) {
        return allData.stream()
                .filter(score -> score.getPlayerId().equals(playerId)) // [cite: 61, 62]
                .mapToInt(Score::getCoinsCollected) // [cite: 63]
                .sum(); // [cite: 64]
    }

    /**
     * Mengembalikan total jarak yang ditempuh seorang pemain[cite: 66].
     */
    public Integer getTotalDistanceByPlayerId(UUID playerId) {
        return allData.stream()
                .filter(score -> score.getPlayerId().equals(playerId))
                .mapToInt(Score::getDistance)
                .sum();
    }
}