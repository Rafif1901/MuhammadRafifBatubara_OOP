package com.rafif.frontend;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Coin {
    private Vector2 position;
    private Rectangle collider;
    private float radius = 15f;
    private boolean active;
    private float bobOffset;
    private float bobSpeed;

    public Coin(Vector2 startPosition){
        this.position = startPosition;
        this.collider = new Rectangle(0, 0, 2 * radius, 2 * radius);
        this.active = true;
        this.radius = 15f;
    }

    public void update(float delta){
        bobOffset += bobSpeed * delta;
        float visualY = position.y + (float)(Math.sin(bobOffset) * 5f);
        collider.setPosition(position.x, visualY);
    }

    public void renderShape(ShapeRenderer shapeRenderer){
        if(!active){
            return;
        }
        float drawY = position.y + (float)(Math.sin(bobOffset) * 5f);
        shapeRenderer.setColor(1f, 1f, 0f, 1f);
        shapeRenderer.circle(position.x, drawY, radius);
    }

    public boolean isColliding(Rectangle playerCollider){
        return active && collider.overlaps(playerCollider);
    }

    public Vector2 getPosition(){
        return position;
    }

    public void setActive(boolean active){
        this.active = active;
    }
}
