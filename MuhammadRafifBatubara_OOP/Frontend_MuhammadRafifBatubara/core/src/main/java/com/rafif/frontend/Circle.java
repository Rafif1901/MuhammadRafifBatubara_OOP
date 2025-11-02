package com.rafif.frontend;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Circle extends Shape{

    public Circle(){
        super();
        this.type = "Circle";
    }

    @Override
    public void render(ShapeRenderer shapeRenderer){
        shapeRenderer.setColor(1, 0, 0, 1);
        shapeRenderer.circle(position.x, position.y, size / 2);
    }
}
