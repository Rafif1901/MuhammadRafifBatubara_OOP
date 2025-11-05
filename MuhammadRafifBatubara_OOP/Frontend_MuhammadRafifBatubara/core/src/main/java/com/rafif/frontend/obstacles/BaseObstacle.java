package com.rafif.frontend.obstacles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class BaseObstacle {
    protected Vector2 position;
    protected Rectangle collider;
    protected float length;
    protected final float WIDTH = 10f;
    protected boolean active = false;

    public BaseObstacle(Vector2 startPosition, int length){
        this.position = new Vector2(startPosition);
        this.length = length;
        updateCollider();
    }

    public void initialize(Vector2 startPosition, int length){
        this.position.set(startPosition);
        this.length = length;
        updateCollider();
    }

    public void render(ShapeRenderer shapeRenderer){
        if(active){
            drawShape(shapeRenderer);
        }
    }

    public boolean isColliding(Rectangle playerCollider){
        if(collider != null){
            if(active && collider.overlaps(playerCollider)){
                return true;
            }
        }
        return false;
    }

    public boolean isActive(){
        return active;
    }

    public boolean isOffScreenCamera(float cameraLeftEdge){
        if(position.x + getRenderWidth() < cameraLeftEdge){
            return true;
        }
        return false;
    }

    protected abstract void updateCollider();
    protected abstract void drawShape(ShapeRenderer shapeRenderer);
    protected abstract float getRenderWidth();

    public void setActive(boolean active){
        this.active = active;
        this.position.set(position.x, position.y);
        updateCollider();
    }

    public void setPosition(float x, float y){
        this.position.set(x, y);
        updateCollider();
    }

    public Vector2 getPosition(){
        return position;
    }

}
