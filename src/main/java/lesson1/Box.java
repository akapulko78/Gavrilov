package lesson1;

import java.util.ArrayList;

public class Box<T extends Fruit> {

    private ArrayList<T> boxOfFruits = new ArrayList<>();


    public double getWeight() {
        return !boxOfFruits.isEmpty() ?
                boxOfFruits.get(0).mass * boxOfFruits.size() : 0;
    }

    public boolean compare(Box<?> other) {
        return Math.abs(getWeight() - other.getWeight()) < 1e-9;
    }


    public void add(T fruit) {
        boxOfFruits.add(fruit);
    }

    public void move(Box<T> other) {
        other.boxOfFruits.addAll(boxOfFruits);
        boxOfFruits.clear();
    }
}


