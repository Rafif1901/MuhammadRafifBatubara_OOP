package com.rafif.frontend;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public abstract class Shape {
    protected Vector2 position;
    protected float size = 50f;
    protected String type;

    public Shape(){
        this.position = new Vector2();
    }

    public void setPosition(float x, float y){
        this.position.set(x, y);
    }
    public String getType(){
        return this.type;
    }
    public void render(ShapeRenderer shapeRenderer){}
}
