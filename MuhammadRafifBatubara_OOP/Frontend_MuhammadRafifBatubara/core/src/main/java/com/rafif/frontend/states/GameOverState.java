package com.rafif.frontend.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverState implements GameState{
    private final GameStateManager gsm;
    private final BitmapFont font;

    public GameOverState(GameStateManager gsm){
        this.gsm = gsm;
        this.font = new BitmapFont();
    }

    @Override
    public void update(float delta){
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            gsm.set(new PlayingState(gsm));
        }
    }

    @Override
    public void render(SpriteBatch batch){
        batch.begin();

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        font.draw(batch, "GAME OVER", screenWidth/ 2f - 40, screenHeight / 2f + 20);
        font.draw(batch, "Press SPACE to restart", screenWidth/2f - 80, screenHeight / 2f - 20);

        batch.end();
    }

    @Override
    public void dispose(){
        font.dispose();
    }
}
