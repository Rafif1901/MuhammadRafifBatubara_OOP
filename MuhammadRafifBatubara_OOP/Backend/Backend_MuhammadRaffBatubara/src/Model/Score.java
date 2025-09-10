package Model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Score implements ShowDetail{
    private final String scoreId;
    private final String playerId;
    private final int value;
    private final int coinsCollected;
    private final int distance;
    private final LocalDateTime createdAt;

    public Score(String playerId, int value,  int coinsCollected, int distance){
        this.scoreId = UUID.randomUUID().toString();
        this.playerId = playerId;
        this.value = value;
        this.coinsCollected = coinsCollected;
        this.distance = distance;
        this.createdAt = LocalDateTime.now();
    }

    public int getValue(){
        return value;
    }

    public int getCoinsCollected(){
        return coinsCollected;
    }

    public int getDistance(){
        return distance;
    }

    public String getPlayerId(){
        return playerId;
    }

    @Override
    public void showDetail(){
        System.out.println("Score ID : " + scoreId);
        System.out.println("Player ID : " + playerId);
        System.out.println("ScoreValue : " + value);
        System.out.println("Coins Collected : " + coinsCollected);
        System.out.println("Distance : " + distance);
        System.out.println("Created At : " + createdAt);
        System.out.println("\n");
    }
}
