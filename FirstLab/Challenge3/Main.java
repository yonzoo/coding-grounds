package Challenge3;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(Controller.readObjectFromFile());
        controller.startMenu();
    }
}
