package com.rafif.frontend.obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private TextureRegion texture;
    private float rotation = 0f;

    public HomingMissile(Vector2 startPosition){
        super(startPosition, 0);
        this.velocity = new Vector2();
        this.rotation = 0f;
        Texture img = new Texture(Gdx.files.internal("missile.png"));
        this.texture = new TextureRegion(img);
    }

    public void initialize(Vector2 startPosition, int length){
        super.initialize(startPosition, length);
        velocity.set(0, 0);
    }

    public void setTarget(Player target){
        this.target = target;
    }

    public boolean isTargetingPlayer(){
        return (target.getPosition().x + target.getWidth()/2f) <= (position.x + width/2f);
    }

    public void update(float delta){
        if(target != null && active){
            if(isTargetingPlayer()){
                velocity.set(target.getPosition()).sub(position).nor().scl (speed);
                position.x += velocity.x * delta;
                position.y += velocity.y * delta;
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

    public void render(SpriteBatch batch){
        if(!active){
            return;
        }
        batch.draw(texture, position.x, position.y, width/2, height/2, width, height, 1, 1, rotation);
    }
}
