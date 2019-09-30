package animals;

import java.io.Serializable;

public class Predator extends Animal implements Serializable {

    public Predator(int id, String name, int weight) {
        super(id, name, weight);
    }

    @Override
    public double calculateFood() {
        return weight <= 30 ? weight * 0.08 : weight * 0.14;
    }
}
