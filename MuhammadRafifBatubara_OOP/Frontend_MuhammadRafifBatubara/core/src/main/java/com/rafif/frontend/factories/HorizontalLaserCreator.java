package com.rafif.frontend.factories;

import com.rafif.frontend.obstacles.BaseObstacle;
import com.rafif.frontend.obstacles.HorizontalLaser;
import com.rafif.frontend.pools.HorizontalLaserPool;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import java.util.List;
import java.util.Random;

public class HorizontalLaserCreator implements ObstacleFactory.ObstacleCreator{
    private final HorizontalLaserPool pool = new HorizontalLaserPool();
    private final static float MIN_LENGTH = 100f;
    private final static float MAX_LENGTH = 300f;

    @Override
    public BaseObstacle create(float groundTopY, float spawnX, float playerHeight, Random rng){
        float length = MIN_LENGTH + rng.nextFloat() * (MAX_LENGTH - MIN_LENGTH);
        float minPositionY = groundTopY + playerHeight;
        float maxPositionY = Gdx.graphics.getHeight() - playerHeight;
        float randomY = minPositionY + rng.nextFloat() * (maxPositionY - minPositionY);
        Vector2 position = new Vector2(spawnX, randomY);
        return pool.obtain(position, (int) length);
    }

    @Override
    public void release(BaseObstacle obstacle){
        if(obstacle instanceof HorizontalLaser){
            pool.release((HorizontalLaser) obstacle);
        }
    }

    @Override
    public void releaseAll(){
        pool.releaseAll();
    }

    @Override
    public List<HorizontalLaser> getInUse(){
        return pool.getInUse();
    }

    @Override
    public boolean supports(BaseObstacle obstacle){
        if(obstacle instanceof HorizontalLaser){
            return true;
        }
        return false;
    }

    @Override
    public String getName(){
        return "HorizontalLaser";
    }
}
