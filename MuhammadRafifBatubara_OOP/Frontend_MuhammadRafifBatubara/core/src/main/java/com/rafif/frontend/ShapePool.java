package com.rafif.frontend;

import java.util.ArrayList;

public class ShapePool{

    private ArrayList<Shape> circlePool = new ArrayList<>();
    private ArrayList<Shape> squarePool = new ArrayList<>();

    private final int MAX_POOL_SIZE = 3;

    public ShapePool(){
        this.circlePool = new ArrayList<>();
        this.squarePool = new ArrayList<>();
    }

    public Shape obtain(String type){
        if(type.equals("Circle") && !circlePool.isEmpty()){
            System.out.println("Circle reused from pool");
            return circlePool.remove(0);
        }
        else if (type.equals("Square") && !squarePool.isEmpty()){
            System.out.println("Square reused from pool");
            return squarePool.remove(0);
        }
        return null;
    }

    public void release (Shape shape){
        if(shape.getType().equals("Circle")){
            if(circlePool.size() < MAX_POOL_SIZE){
                circlePool.add(shape);
            }
        }
        else if(shape.getType().equals("Square")){
            if(squarePool.size() < MAX_POOL_SIZE){
                squarePool.add(shape);
            }
        }
    }

    public ArrayList<Shape> getPool(String type){
        if(type.equals("Circle")){
            return circlePool;
        }
        else if(type.equals("Square")){
            return squarePool;
        }
        return null;
    }
}
