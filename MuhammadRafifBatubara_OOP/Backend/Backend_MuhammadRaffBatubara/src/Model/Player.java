package Model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Player implements ShowDetail {
    private final String playerId;
    private int highScore;
    private int totalCoins;
    private int totalDistance;
    private String username;
    private final LocalDateTime createdAt;

    public Player(String username){
        this.playerId = UUID.randomUUID().toString();
        this.username = username;
        this.highScore = 0;
        this.totalCoins = 0;
        this.totalDistance = 0;
        this.createdAt = LocalDateTime.now();
    }

    public String getPlayerId(){
        return playerId;
    }

    public void updateHighScore(int newScore){
        if (newScore > this.highScore){
            this.highScore = newScore;
        }
    }

    public void addCoins(int coins){
        this.totalCoins += coins;
    }

    public void addDistance(int distance){
        this.totalDistance += distance;
    }

    public void showDetail(){
        System.out.println("Player ID      : " + playerId);
        System.out.println("Username       : " + username);
        System.out.println("High Score     : " + highScore);
        System.out.println("Total Coins    : " + totalCoins);
        System.out.println("Total Distance : " + totalDistance);
        System.out.println("Created At     : " + createdAt);
        System.out.println("\n");
    }
}
