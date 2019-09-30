package animals;

import java.io.Serializable;

public class Omnivorous extends Animal implements Serializable {

    public Omnivorous(int id, String name, int weight) {
        super(id, name, weight);
    }

    @Override
    public double calculateFood() {
        return weight <= 30 ? weight * 0.03 : weight * 0.10;
    }
}
