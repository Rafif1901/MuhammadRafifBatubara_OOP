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

    public void render(int score){
        batch.begin();

        float topMargin = 10f;
        float leftMargin = 10f;
        font.draw(batch, "Score" + score, leftMargin, Gdx.graphics.getHeight() - topMargin);

        batch.end();
    }


    public void dispose(){
        font.dispose();
        batch.dispose();
    }
}
