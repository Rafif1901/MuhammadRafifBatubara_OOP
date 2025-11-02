package com.rafif.frontend;

public class ShapeFactory {

    private ShapePool pool;

    public ShapeFactory(ShapePool pool){
        this.pool = pool;
    }

    public Shape createShape(String type) {
        Shape shape = pool.obtain(type);
        if (shape == null) {
            if (type.equals("Circle")) {
                shape = new Circle();
                System.out.println("New Circle created");
            } else if (type.equals("Square")) {
                shape = new Square();
                System.out.println("New Square created");
            }
        }
        return shape;
    }
}
