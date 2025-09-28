import Model.Player;
import Model.Score;
import Repository.PlayerRepository;
import Repository.ScoreRepository;
import Service.PlayerService;
import Service.ScoreService;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        PlayerRepository playerRepository = new PlayerRepository();
        ScoreRepository scoreRepository = new ScoreRepository();

        PlayerService playerService = new PlayerService(playerRepository);
        ScoreService scoreService = new ScoreService(scoreRepository, playerRepository, playerService);

        System.out.println("=== CS 4 ===\n");

        // Membuat 3 player baru
        Player player1 = new Player("NanaBanana");
        Player player2 = new Player("Yingko");
        Player player3 = new Player("LegdontWork");

        // Simpan ketiga player melalui service
        playerService.createPlayer(player1);
        playerService.createPlayer(player2);
        playerService.createPlayer(player3);

        System.out.println("Players created\n");

        // Tampilkan semua pemain
        System.out.println("All players:");
        playerService.getAllPlayers().forEach(Player::showDetail);

        // Buat 5 skor baru
        Score score1 = new Score(player1.getPlayerId(), 1500, 50, 3000);
        Score score2 = new Score(player1.getPlayerId(), 2000, 75, 4500);
        Score score3 = new Score(player2.getPlayerId(), 1800, 60, 3500);
        Score score4 = new Score(player3.getPlayerId(), 1200, 40, 2500);
        Score score5 = new Score(player3.getPlayerId(), 2500, 90, 5000);

        scoreService.createScore(score1);
        scoreService.createScore(score2);
        scoreService.createScore(score3);
        scoreService.createScore(score4);
        scoreService.createScore(score5);

        System.out.println("Scores created!\n");

        // Tampilkan detail semua player (statistik sudah update)
        System.out.println("Player Score:");
        playerService.getAllPlayers().forEach(Player::showDetail);

        // Leaderboard top 2 player berdasarkan highscore
        System.out.println("Top 2 players by high score:");
        playerService.getLeaderboardByHighScore(2).forEach(Player::showDetail);

        // Tampilkan semua skor milik player1
        System.out.println("All scores for " + player1.getUsername() + ":");
        scoreService.getScoresByPlayerId(player1.getPlayerId()).forEach(Score::showDetail);

        // Leaderboard top 3 skor keseluruhan
        System.out.println("Top 3 scores overall:");
        scoreService.getLeaderboard(3).forEach(Score::showDetail);

        // Cari player berdasarkan username
        System.out.println("Searching for player 'NanaBanana':");
        Optional<Player> foundPlayer = playerService.getPlayerByUsername("NanaBanana");
        if (foundPlayer.isPresent()) {
            foundPlayer.get().showDetail();
        } else {
            System.out.println("Player not found!");
        }

        // Tampilkan total coins dan distance player3
        System.out.println("Totals for " + player3.getUsername() + ":");
        System.out.println("Total Coins: " + scoreService.getTotalCoinsByPlayerId(player3.getPlayerId()));
        System.out.println("Total Distance: " + scoreService.getTotalDistanceByPlayerId(player3.getPlayerId()));
        System.out.println();

        // Tampilkan skor terbaru berdasarkan waktu pembuatan
        System.out.println("Recent scores (ordered by creation time):");
        scoreService.getRecentScores().forEach(Score::showDetail);
    }
}
