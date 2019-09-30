package animals;

import java.io.Serializable;

abstract class Animal implements Serializable {
    protected int id;
    protected String name;
    protected double weight;

    public String getName() {
        return name;
    }

    public int getId() { return id; }

    public Animal(int id, String name, double weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public abstract double calculateFood();
}
