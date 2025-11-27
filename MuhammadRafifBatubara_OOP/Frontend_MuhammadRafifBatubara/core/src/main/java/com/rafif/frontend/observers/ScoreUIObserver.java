package com.rafif.frontend.observers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScoreUIObserver implements  Observer{
    private BitmapFont font;
    private SpriteBatch batch;

    public ScoreUIObserver(){
        this.font = new BitmapFont();
        this.font.setColor(Color.WHITE);
        this.batch = new SpriteBatch();
    }

    @Override
    public void update(int score){
        Gdx.app.log("ScoreUIObserver", "Score" + score);
    }

    public void render(int score, int coins){
        batch.begin();

        font.draw(batch, "Distance: " + score + " m", 20, Gdx.graphics.getHeight() - 20);
        font.draw(batch, "Coins: " + coins, 20, Gdx.graphics.getHeight() - 50);

        batch.end();
    }


    public void dispose(){
        font.dispose();
        batch.dispose();
    }
}
