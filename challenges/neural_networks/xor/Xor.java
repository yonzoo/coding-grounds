import neural_network_lib.NeuralNetwork;
import neural_network_lib.TrainingData;
import processing.core.PApplet;

import java.util.Random;


public class Xor extends PApplet {
    NeuralNetwork nn = new NeuralNetwork(2, 4, 1);
    TrainingData[] arr = new TrainingData[]{
            new TrainingData(new double[]{0.0, 1.0}, new double[]{0.0}),
            new TrainingData(new double[]{1.0, 0.0}, new double[]{0.0}),
            new TrainingData(new double[]{0.0, 0.0}, new double[]{1.0}),
            new TrainingData(new double[]{1.0, 1.0}, new double[]{1.0})
    };

    public static void main(String[] args) {
        PApplet.main("Xor", args);
    }

    public void settings() {
        size(400, 400);
    }

    public void draw() {
        background(0);
        Random r = new Random();
        for (int i = 0; i < 50000; i++) {
            int randomIndex = r.nextInt(arr.length);
            nn.train(arr[randomIndex].getInputs(), arr[randomIndex].getTargets());
        }

        float resolution = 5;
        float cols = (float)Math.floor(width / resolution);
        float rows = (float)Math.floor(height / resolution);

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                float x1 = i / cols;
                float x2 = j / rows;
                double[] inputs = new double[]{x1, x2};
                float y = (float)nn.predict(inputs)[0];
                fill(y * 255);
                rect(i * resolution, j * resolution, resolution, resolution);
            }
        }
    }
}
