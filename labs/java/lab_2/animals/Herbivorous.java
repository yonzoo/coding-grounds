package animals;

import java.io.Serializable;

public class Herbivorous extends Animal implements Serializable {

    public Herbivorous(int id, String name, int weight) {
        super(id, name, weight);
    }

    @Override
    public double calculateFood() {
        return getWeight() <= 30 ? getWeight() * 0.05 : getWeight() * 0.1;
    }

}
