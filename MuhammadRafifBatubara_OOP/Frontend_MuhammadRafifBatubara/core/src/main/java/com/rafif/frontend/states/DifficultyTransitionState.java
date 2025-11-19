package com.rafif.frontend.states;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.rafif.frontend.strategies.DifficultyStrategy;

public class DifficultyTransitionState implements GameState{
    private final GameStateManager gsm;
    private PlayingState playingState;
    private DifficultyStrategy newStrategy;
    private BitmapFont font;
    private float timer = 2f;

    public DifficultyTransitionState(GameStateManager gsm, PlayingState playingState, DifficultyStrategy newStrategy){
        this.gsm = gsm;
        this.playingState = playingState;
        this.newStrategy = newStrategy;
    }
}

