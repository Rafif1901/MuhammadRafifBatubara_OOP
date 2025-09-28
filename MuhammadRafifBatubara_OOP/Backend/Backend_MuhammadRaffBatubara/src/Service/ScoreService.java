package Service;

import Model.Player;
import Model.Score;
import Repository.PlayerRepository;
import Repository.ScoreRepository;

import java.util.*;

/**
 * ScoreService bertanggung jawab untuk mengelola logika bisnis terkait Score.
 * Menggunakan ScoreRepository, PlayerRepository, dan PlayerService.
 */
public class ScoreService {

    // Attributes
    private final ScoreRepository scoreRepository;
    private final PlayerRepository playerRepository;
    private final PlayerService playerService;

    /**
     * Konstruktor ScoreService untuk inisialisasi semua repository dan service.
     *
     * @param scoreRepository  repository score
     * @param playerRepository repository player
     * @param playerService    service player
     */
    public ScoreService(ScoreRepository scoreRepository,
                        PlayerRepository playerRepository,
                        PlayerService playerService) {
        this.scoreRepository = scoreRepository;
        this.playerRepository = playerRepository;
        this.playerService = playerService;
    }

    /**
     * Membuat score baru untuk player tertentu.
     * Sekaligus update statistik player.
     *
     * @param score objek Score
     * @return Score yang sudah tersimpan
     * @throws RuntimeException jika player tidak ditemukan
     */
    public Score createScore(Score score) {
        Player player = playerRepository.findById(score.getPlayerId())
                .orElseThrow(() -> new RuntimeException("Player tidak ditemukan!"));

        scoreRepository.save(score);

        // update statistik player sesuai nilai score
        playerService.updatePlayerStats(
                score.getPlayerId(),
                score.getValue(),
                score.getCoinsCollected(),
                score.getDistance()
        );

        return score;
    }

    /**
     * Ambil score berdasarkan id.
     *
     * @param scoreId UUID score
     * @return Optional Score
     */
    public Optional<Score> getScoreById(UUID scoreId) {
        return scoreRepository.findById(scoreId);
    }

    /**
     * Ambil semua score.
     *
     * @return daftar Score
     */
    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    /**
     * Ambil semua score milik player tertentu.
     *
     * @param playerId UUID player
     * @return daftar Score milik player
     */
    public List<Score> getScoresByPlayerId(UUID playerId) {
        return scoreRepository.findByPlayerId(playerId);
    }

    /**
     * Ambil semua score milik player, urut dari value terbesar.
     *
     * @param playerId UUID player
     * @return daftar Score urut desc
     */
    public List<Score> getScoresByPlayerIdOrderByValue(UUID playerId) {
        return scoreRepository.findByPlayerIdOrderByValueDesc(playerId);
    }

    /**
     * Leaderboard skor tertinggi.
     *
     * @param limit jumlah data
     * @return daftar Score top limit
     */
    public List<Score> getLeaderboard(int limit) {
        return scoreRepository.findTopScores(limit);
    }

    /**
     * Ambil skor tertinggi milik player.
     *
     * @param playerId UUID player
     * @return Optional Score tertinggi
     */
    public Optional<Score> getHighestScoreByPlayerId(UUID playerId) {
        return scoreRepository.findHighestScoreByPlayerId(playerId);
    }

    /**
     * Ambil skor lebih besar dari nilai tertentu.
     *
     * @param minValue batas nilai minimum
     * @return daftar Score
     */
    public List<Score> getScoresAboveValue(int minValue) {
        return scoreRepository.findByValueGreaterThan(minValue);
    }

    /**
     * Ambil semua score urut berdasarkan waktu pembuatan (terbaru dulu).
     *
     * @return daftar Score terbaru
     */
    public List<Score> getRecentScores() {
        return scoreRepository.findAllByOrderByCreatedAtDesc();
    }

    /**
     * Ambil total coins milik player.
     *
     * @param playerId UUID player
     * @return total coins
     */
    public Integer getTotalCoinsByPlayerId(UUID playerId) {
        return scoreRepository.getTotalCoinsByPlayerId(playerId);
    }

    /**
     * Ambil total distance milik player.
     *
     * @param playerId UUID player
     * @return total distance
     */
    public Integer getTotalDistanceByPlayerId(UUID playerId) {
        return scoreRepository.getTotalDistanceByPlayerId(playerId);
    }

    /**
     * Update data score tertentu.
     *
     * @param scoreId      id score
     * @param updatedScore data baru
     * @return Score yang diperbarui
     * @throws RuntimeException jika score tidak ditemukan
     */
    public Score updateScore(UUID scoreId, Score updatedScore) {
        Score existingScore = getScoreById(scoreId)
                .orElseThrow(() -> new RuntimeException("Score tidak ditemukan!"));

        existingScore.setValue(updatedScore.getValue());
        existingScore.setCoinsCollected(updatedScore.getCoinsCollected());
        existingScore.setDistance(updatedScore.getDistance());

        return existingScore;
    }

    /**
     * Hapus score berdasarkan id.
     *
     * @param scoreId UUID score
     * @throws RuntimeException jika score tidak ditemukan
     */
    public void deleteScore(UUID scoreId) {
        Score existingScore = getScoreById(scoreId)
                .orElseThrow(() -> new RuntimeException("Score tidak ditemukan!"));

        scoreRepository.deleteById(existingScore.getScoreId());
    }

    /**
     * Hapus semua score milik player tertentu.
     *
     * @param playerId UUID player
     */
    public void deleteScoresByPlayerId(UUID playerId) {
        List<Score> scores = scoreRepository.findByPlayerId(playerId);
        scores.forEach(score -> scoreRepository.deleteById(score.getScoreId()));
    }
}
