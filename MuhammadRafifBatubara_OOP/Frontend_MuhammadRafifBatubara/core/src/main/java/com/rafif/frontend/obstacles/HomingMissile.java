package com.rafif.frontend.obstacles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.rafif.frontend.Player;

public class HomingMissile extends BaseObstacle{
    private Player target;
    private Vector2 velocity;
    private float speed = 200f;
    private float width = 40f;
    private float height = 20f;

    public HomingMissile(Vector2 startPosition){
        super(startPosition, 0);
        this.velocity = new Vector2();
    }

    public void initialize(Vector2 startPosition, int length){
        super.initialize(startPosition, length);
        velocity.set(0, 0);
    }

    public boolean isTargetingPlayer(){
        if(target == null){
            return false;
        }
        float targetCenter = target.getPosition().x + (width / 2f);
        float missileCenter = this.position.x + (this.width / 2f);
        if(targetCenter > missileCenter){
            return true;
        }
        return false;
    }

    public void update(float delta){
        if(target != null && active){
            if(isTargetingPlayer()){
                velocity.set(target.getPosition()).sub(position).nor().scl (speed);
                position.x += velocity.x * delta;
                position.y =+ velocity.y * delta;
                updateCollider();
            }
        }
    }

    @Override
    protected void updateCollider(){
        this.collider = new Rectangle(position.x, position.y, width, height);
    }

    @Override
    protected void drawShape(ShapeRenderer shapeRenderer){
        shapeRenderer.rect(position.x, position.y, width, height);
    }

    @Override
    protected float getRenderWidth(){
        return width;
    }

    @Override
    protected  float getRenderHeight(){
        return height;
    }
}
