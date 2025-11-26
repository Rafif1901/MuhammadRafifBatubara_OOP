package com.rafif.frontend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.rafif.frontend.observers.Observer;
import com.rafif.frontend.observers.ScoreManager;
import com.rafif.frontend.services.BackendService;

public class GameManager {

    private static GameManager instance;
    private ScoreManager scoreManager;
    private boolean gameActive;
    private BackendService backendService;
    private String currentPlayerId = null;
    private int coinsCollected = 0;

    private GameManager(){
        this.scoreManager = new ScoreManager();
        this.gameActive = false;
        this.backendService = new BackendService();
    }

    public static GameManager getInstance(){
        if(instance == null){
            instance = new GameManager();
        }
        return instance;
    }

    public void registerPlayer(String username){
        backendService.createPlayer(username, new BackendService.RequestCallback() {
            @Override
            public void onSuccess(String response) {
                try{
                    JsonValue root = new JsonReader().parse(response);
                    currentPlayerId = root.getString("playerId");
                    Gdx.app.log("GameManager", "Register Success. ID: " + currentPlayerId);
                }catch(Exception e){
                    Gdx.app.log("GameManager", "JSON Parsing Error: " + e.getMessage());
                }
            }
            @Override
            public void onError(String error) {
                Gdx.app.log("GameManager", "Register Failed " + error);
            }
        });
    }

    public void startGame(){
        scoreManager.setScore(0);
        gameActive = true;
        System.out.println("Game Started!");
        coinsCollected = 0;
    }

    public void endGame(){
        if(currentPlayerId == null){
            Gdx.app.log("GameManager", "Cannot Submit Score: No Player ID");
            return;
        }
        int distance = scoreManager.getScore();
        int totalScore = distance + coinsCollected * 10;
        Gdx.app.log("GameManager", "Submitting Score... Dist" + distance + "Coins " + coinsCollected);
        backendService.submitScore(currentPlayerId, totalScore, coinsCollected, distance, new BackendService.RequestCallback() {
            @Override
            public void onSuccess(String response) {
                Gdx.app.log("GameManager", "Submit Score Successfully" + response);
            }
            @Override
            public void onError(String error) {
                Gdx.app.log("GameManager", "Submit Score Failed" + error);
            }
        });
    }

    public void addCoin(){
        coinsCollected++;
        Gdx.app.log("GameManager", "COIN COLLECTED! Total: " + coinsCollected);
    }
    public void setScore(int distance){
        if(gameActive == true){
            scoreManager.setScore(distance);
        }
    }

    public int getScore(){
        return scoreManager.getScore();
    }

    public void addObserver(Observer observer){
        scoreManager.addObserver(observer);
    }

    public void removeObserver(Observer observer){
        scoreManager.removeObserver(observer);
    }

    public int getCoins(){
        return coinsCollected;
    }
}
