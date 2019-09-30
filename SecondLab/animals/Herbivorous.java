package animals;

import java.io.Serializable;

public class Herbivorous extends Animal implements Serializable {

    public Herbivorous(int id, String name, int weight) {
        super(id, name, weight);
    }

    @Override
    public double calculateFood() {
        return weight <= 30 ? weight * 0.05 : weight * 0.1;
    }

}
