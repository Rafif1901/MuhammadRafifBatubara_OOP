package com.rafif.frontend.strategies;

import com.rafif.frontend.Coin;
import com.rafif.frontend.factories.CoinFactory;
import java.util.List;

public interface CoinPattern {
    List<Coin> spawn(CoinFactory factory, float groundTopY, float spawnX, float screenHeight);
    String getName();
}
