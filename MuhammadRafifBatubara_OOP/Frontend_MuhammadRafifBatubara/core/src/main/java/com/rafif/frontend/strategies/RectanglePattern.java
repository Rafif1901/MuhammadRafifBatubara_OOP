package com.rafif.frontend.strategies;

import com.rafif.frontend.Coin;
import com.rafif.frontend.factories.CoinFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RectanglePattern implements CoinPattern{
    private static float SPACING_X = 40f;
    private static float SPACING_Y = 40f;
    private Random random;

    public RectanglePattern(){
        this.random = new Random();
    }

    @Override
    public List<Coin> spawn(CoinFactory factory, float groundTopY, float spawnX, float screenHeight){
        List<Coin> coinList = new ArrayList<>();
        int col = 3 + random.nextInt(2);
        int row = 2 + random.nextInt(2);
        float minY = groundTopY + 50;
        float maxY = screenHeight - 100;
        float startY = minY + random.nextFloat(maxY - minY);

        for(int i = 0; i < col; i++){
            for(int j = 0; j < row; j++){
                float positionX = spawnX + (i * SPACING_X);
                float positionY = startY + (j * SPACING_Y);
                coinList.add(factory.coinPool.obtain(positionX, positionY));
            }
        }
        return coinList;
    }

    @Override
    public String getName(){
        return "Rectangle";
    }
}
