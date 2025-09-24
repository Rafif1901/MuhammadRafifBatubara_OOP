package Model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Score implements ShowDetail {
    // Tipe data diubah menjadi UUID agar sesuai dengan Repository
    private UUID scoreId;
    private UUID playerId;
    private int value;
    private int coinsCollected;
    private int distance;
    private final LocalDateTime createdAt;

    // Constructor dilengkapi
    public Score(UUID playerId, int value, int coinsCollected, int distance) {
        this.scoreId = UUID.randomUUID(); // Membuat ID unik untuk setiap skor baru
        this.playerId = playerId;
        this.value = value;
        this.coinsCollected = coinsCollected;
        this.distance = distance;
        this.createdAt = LocalDateTime.now(); // Mencatat waktu skor dibuat
    }

    //== Method Getter yang Ditambahkan/Diperbaiki ==//
    public UUID getScoreId() {
        return scoreId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Return type diubah menjadi UUID
    public UUID getPlayerId() {
        return playerId;
    }


    //== Method Getter yang Sudah Ada ==//
    public int getValue() {
        return value;
    }

    public int getCoinsCollected() {
        return coinsCollected;
    }

    public int getDistance() {
        return distance;
    }


    @Override
    public void showDetail() {
        System.out.println("Score ID        : " + scoreId);
        System.out.println("Player ID       : " + playerId);
        System.out.println("Score Value     : " + value);
        System.out.println("Coins Collected : " + coinsCollected);
        System.out.println("Distance        : " + distance);
        System.out.println("Created At      : " + createdAt);
        System.out.println(); // Memberi jarak untuk output berikutnya
    }
}