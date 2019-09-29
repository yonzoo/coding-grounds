package SecondLab;

public class Main {

    public static void main(String[] args) {
        Controller.addAnimal(new Herbivorous(1, "Melman", 130));
	Controller.addAnimal(new Herbivorous(2, "Marti", 100));
	Controller.addAnimal(new Omnivorous(3, "Gloria", 400));
	Controller.addAnimal(new Omnivorous(12, "Aloria", 400));
	Controller.addAnimal(new Predator(4, "Alex", 90));
	Controller.sortAnimals();
	Controller.saveData();
	System.out.println("Task a:");
	Controller.printList();
	System.out.println("Task b:");
	Controller.printFirstFiveNames();
	System.out.println("Task c:");
	Controller.printLastThreeIds();
    }
}
