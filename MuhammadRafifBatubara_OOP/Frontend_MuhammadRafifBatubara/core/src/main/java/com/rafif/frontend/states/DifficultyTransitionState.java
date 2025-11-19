package com.rafif.frontend.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.rafif.frontend.strategies.DifficultyStrategy;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DifficultyTransitionState implements GameState{
    private final GameStateManager gsm;
    private PlayingState playingState;
    private DifficultyStrategy newStrategy;
    private BitmapFont font;
    private float timer = 2.0f;

    public DifficultyTransitionState(GameStateManager gsm, PlayingState playingState, DifficultyStrategy newStrategy){
        this.gsm = gsm;
        this.playingState = playingState;
        this.newStrategy = newStrategy;
        this.font = new BitmapFont();
        this.timer = 2.0f;
    }

    @Override
    public void update(float delta){
        timer -= delta;
        if(timer <= 0){
            playingState.setDifficulty(newStrategy);
            gsm.pop();
        }
    }

    @Override
    public void render(SpriteBatch batch){
        playingState.render(batch);

        batch.begin();

        font.draw(batch, "DIFFICULTY INCREASED!\n\n\n" + newStrategy.getClass().getSimpleName() , Gdx.graphics.getWidth()/ 2f - 80, Gdx.graphics.getHeight()/ 2f + 20);
        batch.end();
    }

    @Override
    public void dispose(){
        font.dispose();
    }
}

