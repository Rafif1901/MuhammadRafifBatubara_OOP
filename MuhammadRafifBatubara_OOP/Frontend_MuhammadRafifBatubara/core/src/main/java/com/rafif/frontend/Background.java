package com.rafif.frontend;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Background {
    private Texture backgroundTexture;
    private TextureRegion backgroundRegion;
    private float width;
    private float height;
    private float currentCameraX = 0f;

    public Background(){
        this.backgroundTexture = new Texture(Gdx.files.internal("background.png"));
        this.backgroundRegion = new TextureRegion(backgroundTexture);
        this.width = 2688f;
        this.height = 1536f;
    }

    public void update(float cameraX){
        this.currentCameraX = cameraX;
    }

    public void render(SpriteBatch batch){
        float screenHeight = Gdx.graphics.getHeight();
        float screenWidth = Gdx.graphics.getWidth();

        float scale = screenHeight / height;

        float scaledHeight = height * scale;
        float scaledWidth = width * scale;

        float cameraLeftEdge = currentCameraX - (screenWidth / 2f);
        float startX = (float)Math.floor(cameraLeftEdge / scaledWidth) * scaledWidth;
        float cameraRightEdge = currentCameraX + (screenWidth / 2f);

        for(float x = startX; x < cameraRightEdge; x += scaledWidth){
            batch.draw(backgroundRegion, x, 0, scaledWidth, scaledHeight);
        }
    }

    public void dispose(){
        if(backgroundTexture != null){
            backgroundTexture.dispose();
        }
    }
}
