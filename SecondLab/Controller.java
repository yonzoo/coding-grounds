package SecondLab;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Controller {
    private static ArrayList<Animal> animals;
    private static File file;

    static {
        file = new File("./animals.ser");
        animals = new ArrayList<>();
        try (
                FileInputStream input = new FileInputStream(file);
                ObjectInputStream reader = new ObjectInputStream(input);
        ) {
            file.createNewFile();
            animals = (ArrayList<Animal>) reader.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found, creating a new one");
        } catch (EOFException e) {
            System.out.println("Warning! File is empty!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void saveData() {
        try (
                FileOutputStream output = new FileOutputStream(file, false);
                ObjectOutputStream writer = new ObjectOutputStream(output);
        ) {
            writer.writeObject(animals);
            System.out.println("Your data was successfully saved");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void addAnimal(Animal animal) {
        animals.add(animal);
    }

    static ArrayList<Animal> getAnimals() {
        return animals;
    }

    static void sortAnimals() {
        animals.sort(new Comparator<Animal>() {
            public int compare(Animal a1, Animal a2) {
                int result = Double.compare(a2.calculateFood(), a1.calculateFood());
                return result == 0 ? a1.getName().compareToIgnoreCase(a2.getName()) : result;
            }
        });
    }

    static void printList() {
        if (animals.size() == 0) System.out.println("List is empty");
        animals.forEach(animal -> {
            System.out.println(animal.getId() + "\n" + animal.getName() + "\n" + animal.getClass().getSimpleName() + "\n" + animal.calculateFood());
            System.out.println("-------------------------------------------------");
        });
    }

    static void printFirstFiveNames() {
        if (animals.size() < 5 && animals.size() > 0)
            System.out.print("List size is less than 5 so you get " + (5 - animals.size()) + " elements");
        if (animals.size() == 0) System.out.println("List is empty");
        for (int i = 0; i < 5 && i < animals.size(); i++) {
            System.out.println(animals.get(i).getName());
        }
    }

    static void printLastThreeIds() {
        if (animals.size() < 5 && animals.size() > 0)
            System.out.print("List size is less than 3 so you get " + (3 - animals.size()) + " elements");
        if (animals.size() == 0) System.out.println("List is empty");
        for (int i = animals.size() - 1; i > animals.size() - 4 && i >= 0; i--) {
            System.out.println(animals.get(i).getId());
        }
    }

}
