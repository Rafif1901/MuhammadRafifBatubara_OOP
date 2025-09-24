import Model.Player;
import Model.Score;
import Repository.PlayerRepository;
import Repository.ScoreRepository;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        PlayerRepository playerRepo = new PlayerRepository();
        ScoreRepository scoreRepo = new ScoreRepository();

        Player player1 = new Player("Stelle");
        Player player2 = new Player("gamerLooxmaxxing");
        Player player3 = new Player("Stelle123");
        Player player4 = new Player("Banananana");

        // Buat dan simpan player
        playerRepo.save(player1);
        playerRepo.save(player2);
        playerRepo.save(player3);
        playerRepo.save(player4);

        // Update stats player 1 & 2
        player1.updateHighScore(1500);
        player1.addCoins(250);
        player1.addDistance(5000);

        player2.updateHighScore(3200);
        player2.addCoins(750);
        player2.addDistance(12000);

        // Buat update stats untuk player 3 dan 4 (bebas)
        player3.updateHighScore(2400);
        player3.addCoins(450);
        player3.addDistance(6600);

        player4.updateHighScore(3200);
        player4.addCoins(1110);
        player4.addDistance(19200);


        // Buat dan simpan nilai dengan ketentuan
        Score score1 = new Score(player2.getPlayerId(), 1500, 250, 5000);
        Score score2 = new Score(player4.getPlayerId(), 3200, 750, 12000);
        Score score3 = new Score(player1.getPlayerId(), 4000, 400, 32000);
        Score score4 = new Score(player4.getPlayerId(), 1800, 300, 6000);
        Score score5 = new Score(player3.getPlayerId(), 2400, 240, 2400);
        Score score6 = new Score(player2.getPlayerId(), 6200, 320, 5000);
        Score score7 = new Score(player4.getPlayerId(), 1800, 60, 1200);
        Score score8 = new Score(player1.getPlayerId(), 2100, 200, 7000);
        Score score9 = new Score(player1.getPlayerId(), 8000, 720, 6200);
        Score score10 = new Score(player3.getPlayerId(), 1900, 210, 4200);

        scoreRepo.save(score1);
        scoreRepo.save(score2);
        scoreRepo.save(score3);
        scoreRepo.save(score4);
        scoreRepo.save(score5);
        scoreRepo.save(score6);
        scoreRepo.save(score7);
        scoreRepo.save(score8);
        scoreRepo.save(score9);
        scoreRepo.save(score10);


        System.out.println("=== TESTING CS3 ===");

        System.out.println("Find player by ID:");
        // Tunjukkan detail Player 3
        playerRepo.findById(player3.getPlayerId()).ifPresent(Player::showDetail);

        System.out.println("All players:");
        // Tunjukkan semua player
        playerRepo.findAll().forEach(Player::showDetail);

        // Urutkan player berdasar highscore
        System.out.println("\nPlayers sorted by highscore:");
        playerRepo.findTopPlayersByHighScore(4).forEach(Player::showDetail);


        System.out.println("\nScores for player1:");
        // Cari Score Player 1
        scoreRepo.findByPlayerId(player1.getPlayerId()).forEach(Score::showDetail);
    }
}