package com.rafif.frontend;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Square extends Shape{

    public Square(){
        super();
        this.type = "Square";
    }

    @Override
    public void render(ShapeRenderer shapeRenderer){
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.rect(position.x - size / 2, position.y - size / 2, size, size);
    }
}
