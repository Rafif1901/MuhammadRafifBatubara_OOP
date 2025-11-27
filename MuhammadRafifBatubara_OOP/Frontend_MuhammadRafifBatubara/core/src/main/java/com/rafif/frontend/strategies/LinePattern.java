package com.rafif.frontend.strategies;

import com.rafif.frontend.Coin;
import com.rafif.frontend.factories.CoinFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LinePattern implements CoinPattern{
    private static float SPACING = 40f;
    private Random random;

    public LinePattern(){
        this.random = new Random();
    }
    @Override
    public List<Coin> spawn(CoinFactory factory, float groundTopY, float spawnX, float screenHeight){
        List<Coin> coinList = new ArrayList<>();
        int coinSize = 3 + random.nextInt(2);
        float minY = groundTopY + 50;
        float maxY = screenHeight - 100;
        float startY = minY + random.nextFloat() * (maxY - minY);

        for(int i = 0; i < coinSize; i++){
            float positionX = (spawnX + i * SPACING);
            coinList.add(factory.coinPool.obtain(positionX, startY));
        }
        return coinList;
    }

    @Override
    public String getName(){
        return "Line";
    }
}
