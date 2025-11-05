package com.rafif.frontend.factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.rafif.frontend.obstacles.BaseObstacle;
import com.rafif.frontend.obstacles.HomingMissile;
import com.rafif.frontend.obstacles.HorizontalLaser;
import com.rafif.frontend.obstacles.VerticalLaser;
import com.rafif.frontend.pools.HomingMissilePool;

import java.util.List;
import java.util.Random;

public class HomingMissileCreator implements ObstacleFactory.ObstacleCreator{
    private final HomingMissilePool pool =  new HomingMissilePool();

    @Override
    public BaseObstacle create(float groundTopY, float spawnX, float playerHeight, Random rng){
        float randomY = groundTopY + rng.nextFloat() * (Gdx.graphics.getHeight() - groundTopY);
        Vector2 position = new Vector2(spawnX, randomY);
        return pool.obtain(position);
    }

    @Override
    public void release(BaseObstacle obstacle){
        if(obstacle instanceof HomingMissile){
            pool.release((HomingMissile) obstacle);
        }
    }

    @Override
    public void releaseAll(){
        pool.releaseAll();
    }

    @Override
    public List<HomingMissile> getInUse(){
        return pool.getInUse();
    }

    @Override
    public boolean supports(BaseObstacle obstacle){
        if(obstacle instanceof HomingMissile){
            return true;
        }
        return false;
    }

    @Override
    public String getName(){
        return "HomingMissile";
    }
}
