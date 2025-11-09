package com.tp9.frontend;

import java.util.ArrayList;

public class ScoreSystem {
    private ArrayList<ScoreObserver> observers = new ArrayList<>();
    private int score = 0;

    public void registerObserver(ScoreObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ScoreObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (ScoreObserver scoreObserver : observers) {
            scoreObserver.onScoreUpdate(score);
        }
    }

    public void addScore(int amount) {
        this.score += amount;
        notifyObservers();
    }
}
