package com.rafif.frontend.factories;

import com.rafif.frontend.obstacles.BaseObstacle;
import com.rafif.frontend.obstacles.HorizontalLaser;
import com.rafif.frontend.obstacles.VerticalLaser;
import com.rafif.frontend.pools.VerticalLaserPool;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import java.util.List;
import java.util.Random;

public class VerticalLaserCreator implements ObstacleFactory.ObstacleCreator {
    private final VerticalLaserPool pool = new VerticalLaserPool();
    private final static float MIN_LENGTH = 100f;
    private final static float MAX_LENGTH = 300f;

    @Override
    public BaseObstacle create(float groundTopY, float spawnX, float playerHeight, Random rng){
        float height = MIN_LENGTH + rng.nextFloat() * (MAX_LENGTH - MIN_LENGTH);
        Vector2 position = new Vector2(spawnX, groundTopY);
        return pool.obtain(position, (int) height);
    }

    @Override
    public void release(BaseObstacle obstacle){
        if(obstacle instanceof VerticalLaser){
            pool.release((VerticalLaser) obstacle);
        }
    }

    @Override
    public void releaseAll(){
        pool.releaseAll();
    }

    @Override
    public List<VerticalLaser> getInUse(){
        return pool.getInUse();
    }

    @Override
    public boolean supports(BaseObstacle obstacle){
        if(obstacle instanceof VerticalLaser){
            return true;
        }
        return false;
    }

    @Override
    public String getName(){
        return "VerticalLaser";
    }
}

