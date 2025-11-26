package com.rafif.frontend.factories;

import com.badlogic.gdx.Gdx;
import com.rafif.frontend.Coin;
import com.rafif.frontend.pools.CoinPool;

import java.util.List;
import java.util.Random;

public class CoinFactory {
    private CoinPool coinPool;
    private Random random;
    private final static float RADIUS_CIRCLE = 15f;

    public void createCoinPattern(float spawnX, float groundTopY){
        float ceilingY = Gdx.graphics.getHeight();
        float minY = groundTopY + RADIUS_CIRCLE;
        float maxY = ceilingY - RADIUS_CIRCLE;
        float spawnY = groundTopY + random.nextFloat() * (maxY - minY);
        if(random.nextFloat() <= 0.3){
            for(int i = 0; i < 3; i++){
                float currentX = spawnX + (i * 40);
                coinPool.obtain(currentX, spawnY);
            }
        }
    }

    public List<Coin> getActiveCoins(){
        return coinPool.getInUse();
    }

    public void release(Coin coin){
        coinPool.release(coin);
    }

    public void releaseAll(){
        coinPool.releaseAll();
    }
}
