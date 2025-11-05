package com.rafif.frontend.factories; // Ganti dengan nama package kalian

import java.util.*;
import com.rafif.frontend.obstacles.BaseObstacle;
import com.rafif.frontend.obstacles.VerticalLaser;
import com.rafif.frontend.obstacles.HorizontalLaser;
import com.rafif.frontend.obstacles.HomingMissile;
import com.rafif.frontend.pools.VerticalLaserPool;
import com.rafif.frontend.pools.HorizontalLaserPool;
import com.rafif.frontend.pools.HomingMissilePool;

public class ObstacleFactory {

    /** Factory Method implementor */
    public interface ObstacleCreator {
        // TODO: Deklarasikan abstract method untuk membuat objek baru.
        //       Menerima groundTopY, spawnX, playerHeight, dan Random,
        //       dan mengembalikan BaseObstacle.
        BaseObstacle create(float groundTopY, float spawnX, float playerHeight, Random random);

        // TODO: Deklarasikan abstract method release() untuk me-release BaseObstacle ke pool.
        //       Menerima BaseObstacle.
        void release(BaseObstacle obstacle);

        // TODO: Deklarasikan abstract method releaseAll() untuk me-release semua objek aktif.
        //       Tidak menerima apapun.
        void releaseAll();

        // TODO: Deklarasikan abstract method getInUse() untuk mendapatkan list objek yang sedang digunakan.
        //       Mengembalikan List<? extends BaseObstacle>.
        //       Tidak menerima apapun.
        List<? extends BaseObstacle> getInUse();

        // TODO: Deklarasikan abstract method supports() untuk mengecek apakah creator ini mendukung
        //       tipe BaseObstacle yang diberikan.
        //       Mengembalikan boolean.
        //       Menerima BaseObstacle.
        boolean supports(BaseObstacle obstacle);

        // TODO: Deklarasikan abstract method getName() untuk mendapatkan nama creator.
        //       Mengembalikan String.
        //       Tidak menerima apapun.
        String getName();
    }

    /** Weighted creator for probability-based spawning */
    private static class WeightedCreator {
        ObstacleCreator creator;
        int weight;

        WeightedCreator(ObstacleCreator creator, int weight) {
            this.creator = creator;
            this.weight = weight;
        }
    }

    private final List<WeightedCreator> weightedCreators = new ArrayList<>();
    private final Random random = new Random();
    private int totalWeight = 0;

    public ObstacleFactory() {
        register(new VerticalLaserCreator(), 2);
        register(new HorizontalLaserCreator(), 2);
        register(new HomingMissileCreator(), 1);
    }

    public void register(ObstacleCreator creator, int weight) {
        weightedCreators.add(new WeightedCreator(creator, weight));
        totalWeight += weight;
    }

    /** Factory Method using weighted random selection */
    public BaseObstacle createRandomObstacle(float groundTopY, float spawnX, float playerHeight) {
        if (weightedCreators.isEmpty()) {
            throw new IllegalStateException("No obstacle creators registered");
        }
        ObstacleCreator creator = selectWeightedCreator();
        return creator.create(groundTopY, spawnX, playerHeight, random);
    }

    private ObstacleCreator selectWeightedCreator() {
        int randomValue = random.nextInt(totalWeight);
        int currentWeight = 0;

        for (WeightedCreator wc : weightedCreators) {
            currentWeight += wc.weight;
            if (randomValue < currentWeight) {
                return wc.creator;
            }
        }

        return weightedCreators.get(0).creator;
    }

    public void releaseObstacle(BaseObstacle obstacle) {
        for (WeightedCreator wc : weightedCreators) {
            if (wc.creator.supports(obstacle)) {
                wc.creator.release(obstacle);
                return;
            }
        }
    }

    public void releaseAllObstacles() {
        for (WeightedCreator wc : weightedCreators) {
            wc.creator.releaseAll();
        }
    }

    public List<BaseObstacle> getAllInUseObstacles() {
        List<BaseObstacle> list = new ArrayList<>();

        for (WeightedCreator wc : weightedCreators) {
            list.addAll(wc.creator.getInUse());
        }

        return list;
    }

    public List<String> getRegisteredCreatorNames() {
        List<String> names = new ArrayList<>();

        for (WeightedCreator wc : weightedCreators) {
            names.add(wc.creator.getName());
        }
        return names;
    }
}
