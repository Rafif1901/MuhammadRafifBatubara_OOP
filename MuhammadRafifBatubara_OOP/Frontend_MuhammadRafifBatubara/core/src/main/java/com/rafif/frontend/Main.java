package com.rafif.frontend;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.Color;

/** {@link ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private ShapeRenderer shapeRenderer;
    private float x, y, width, height, speed;
    private int colorState;
    private String colorName;
    private Color[] colors;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        Gdx.app.setLogLevel(com.badlogic.gdx.Application.LOG_DEBUG);
        colors = new Color[]{Color.RED, Color.YELLOW, Color.BLUE};
        colorState = 0;
        width = 50f;
        height = 50f;
        speed = 300f;
        x = Gdx.graphics.getWidth() / 2f - width / 2f;
        y = Gdx.graphics.getHeight() / 2f - height / 2f;
    }

    private void handleInput(){
        float deltaTime = Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)){
            y += speed * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)){
            y -= speed * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)){
            x -= speed * deltaTime;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)){
            x += speed * deltaTime;
        }

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            colorState = (colorState + 1) % colors.length;

            if (colorState == 0){
                colorName = "Merah";
            }
            else if (colorState == 1){
                colorName = "Kuning";
            }
            else if (colorState == 2){
                colorName = "Biru";
            }

            Gdx.app.log("ColorChange", "Warna berubah ke: " + colorName);
        }
    }

    private void updateLogic(){
        x = MathUtils.clamp(x, 0, Gdx.graphics.getWidth() - width);
        y = MathUtils.clamp(y, 0, Gdx.graphics.getHeight() - height);
    }

    @Override
    public void render() {
        handleInput();
        updateLogic();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(colors[colorState]);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
