package animals;

import java.io.Serializable;

abstract class Animal implements Serializable {
    private int id;
    private String name;
    private double weight;

    public String getName() {
        return name;
    }

    public int getId() { return id; }

    public double getWeight() { return weight; }

    public Animal(int id, String name, double weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public abstract double calculateFood();
}
