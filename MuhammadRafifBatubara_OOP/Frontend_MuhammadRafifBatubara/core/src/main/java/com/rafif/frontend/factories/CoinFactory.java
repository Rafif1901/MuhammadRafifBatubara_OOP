package com.rafif.frontend.factories;

import com.badlogic.gdx.Gdx;
import com.rafif.frontend.Coin;
import com.rafif.frontend.pools.CoinPool;

import java.util.List;
import java.util.Random;

public class CoinFactory {
    public final CoinPool coinPool;
    private final static float RADIUS_CIRCLE = 15f;

    public CoinFactory(){
        this.coinPool = new CoinPool();
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
