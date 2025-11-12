package com.rafif.frontend;

import com.rafif.frontend.observers.ScoreManager;

public class GameManager {

    private static GameManager instance;
    private ScoreManager scoreManager;
    private boolean gameActive;

    private GameManager(){
        this.scoreManager = new ScoreManager();
        this.gameActive = false;
    }

    public static GameManager getInstance(){
        if(instance == null){
            instance = new GameManager();
        }
        return instance;
    }

    public void startGame(){
        scoreManager.setScore(0);
        gameActive = true;
        System.out.println("Game Started!");
    }

    public void setScore(int newScore){
        if(gameActive == true){
            scoreManager.setScore(newScore);
        }
    }

    public int getScore(){
        return scoreManager.getScore();
    }
}
