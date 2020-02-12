package controller.console;

import data.DbHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static data.DbRepository.*;

public class ConsoleController implements Commands {
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        launch();
    }

    public static void launch() {
        boolean finish = false;
        while (!finish) {
            System.out.println("Please enter command and arguments...");
            String actionRaw = "";
            while (actionRaw.length() == 0) actionRaw = scn.nextLine();
            List<String> parts = Arrays.asList(actionRaw.trim().split("[ ]+"));

            if (parts.size() > 3 || parts.size() == 0)
                System.err.println("Invalid number of parameters. It should be 1-3. Please follow pattern: /command arguments...");

            String command = parts.get(0);
            List<String> arguments = null;
            if (parts.size() > 1) {
                arguments = parts.subList(1, parts.size());
            }
            switch (command) {
                case ADD_COMMAND: {
                    try {
                        assert arguments != null;
                        if (arguments.size() == 2) {
                            String title = arguments.get(0);
                            double price = Double.parseDouble(arguments.get(1));
                            if (price >= 0)
                                executeAdd(title, price);
                            else throw new Exception();
                        } else throw new Exception();
                    } catch (Exception e) {
                        System.err.println("Add command should match pattern: /add $title $price, $price must be >= 0");
                    }
                    break;
                }
                case DELETE_COMMAND: {
                    try {
                        assert arguments != null;
                        if (arguments.size() == 1) {
                            String title = arguments.get(0);
                            executeDelete(title);
                        } else throw new Exception();
                    } catch (Exception e) {
                        System.err.println("Delete command should match pattern: /delete $title");
                    }
                    break;
                }
                case SHOW_ALL_COMMAND: {
                    try {
                        if (arguments == null) {
                            executeShowAll();
                        } else throw new Exception();
                    } catch (Exception e) {
                        System.err.println("Show all command should match pattern: /show_all");
                    }
                    break;
                }
                case GET_PRICE_COMMAND: {
                    try {
                        assert arguments != null;
                        if (arguments.size() == 1) {
                            String title = arguments.get(0);
                            executeGetPrice(title);
                        } else throw new Exception();
                    } catch (Exception e) {
                        System.err.println("Get price command should match pattern: /price $title");
                    }
                    break;
                }
                case CHANGE_PRICE_COMMAND: {
                    try {
                        assert arguments != null;
                        if (arguments.size() == 2) {
                            String title = arguments.get(0);
                            double newPrice = Double.parseDouble(arguments.get(1));
                            if (newPrice >= 0)
                                executeChangePrice(title, newPrice);
                            else throw new Exception();
                        } else throw new Exception();
                    } catch (Exception e) {
                        System.err.println("Change price command should match pattern: /change_price $title $new_price, $new_price must be >= 0");
                    }
                    break;
                }
                case FILTER_BY_PRICE_COMMAND: {
                    try {
                        assert arguments != null;
                        if (arguments.size() == 2) {
                            double rangeMin = Double.parseDouble(arguments.get(0));
                            double rangeMax = Double.parseDouble(arguments.get(1));
                            if (rangeMax >= 0 && rangeMax >= 0 && rangeMin <= rangeMax)
                                executeFilterByPrice(rangeMin, rangeMax);
                            else throw new Exception();
                        } else throw new Exception();
                    } catch (Exception e) {
                        System.err.println("Filter by price command should match pattern: /filter_by_price $range_min $range_max," +
                                " $range_min and $range_max must be >= 0, $range_min must be <= $range_max");
                    }
                    break;
                }
                case EXIT: {
                    finish = true;
                    DbHandler.finish();
                    break;
                }
                default: {
                    System.err.println("Invalid command, please try again...");
                }
            }
        }
    }
}
