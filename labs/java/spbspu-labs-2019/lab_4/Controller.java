package lab_4;

import lab_4.libs.FileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Controller {
    private static final String HELP_CMD = "help";
    private static final String STOP_CMD = "q";
    public static final String LIST_CMD = "ls";
    public static final String DELETE_CMD = "rm";
    public static final String WRITE_CMD = "write";
    public static final String CHANGE_DIRECTORY_CMD = "cd";
    public static final String MAKE_DIRECTORY_CMD = "mkdir";
    public static final String CREATE_FILE_CMD = "touch";

    public void start() {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome Alien! To see available commands enter help");
        while (true) {
            System.out.print(getPrefix());
            String[] cmd = in.nextLine().trim().split(" ");
            if (cmd.length == 0 || !ifValidParams(cmd)) continue;
            switch (cmd[0]) {
                case LIST_CMD:
                    ls(cmd);
                    break;
                case WRITE_CMD:
                    write(cmd[1]);
                    break;
                case CHANGE_DIRECTORY_CMD:
                    cd(cmd[1]);
                    break;
                case MAKE_DIRECTORY_CMD:
                    mkdir(cmd[1]);
                    break;
                case CREATE_FILE_CMD:
                    touch(cmd[1]);
                    break;
                case DELETE_CMD:
                    rm(cmd[1]);
                    break;
                case HELP_CMD:
                    writeHelpInfo();
                    break;
                case STOP_CMD:
                    return;
            }
        }
    }

    public void ls(String[] cmd) {
        try {
            if (cmd.length > 1) {
                File file = new File(cmd[1]);
                if (file.exists() && file.isDirectory()) {
                    FileManager.ls(Paths.get(file.getAbsolutePath())).forEach(x -> System.out.println(x.getFileName()));
                } else {
                    System.err.println("No such directory");
                }
            } else {
                FileManager.ls().forEach(x -> System.out.println(x.getFileName()));
            }
        } catch (NullPointerException | IOException ex) {
            System.err.println("Sorry, I could not find a directory you were asking for :(");
        }
    }

    public void rm(String cmd) {
        try {
            File file = new File(cmd);
            if (file.exists()) {
                FileManager.rm(file.getAbsolutePath());
            } else {
                FileManager.rm(cmd);
            }
        } catch (NullPointerException | IOException ex) {
            System.err.println("Sorry, I could not find a file you were asking for :(");
        }
    }

    public void touch(String name) {
        try {
            FileManager.touch(name);
        } catch (IOException | NullPointerException ex) {
            System.err.println("Failed to create a file, please try again");
        }
    }

    public void mkdir(String name) {
        try {
            FileManager.mkdir(name);
        } catch (IOException | NullPointerException ex) {
            System.err.println("Failed to create a directory, please try again");
        }
    }

    public void cd(String name) {
        try {
            FileManager.cd(name);
        } catch (NullPointerException ex) {
            System.err.println("Sorry, I could not find a directory you were asking for :(");
        }
    }

    public void write(String name) {
        try {
            Scanner in = new Scanner(System.in);
            String input = "";
            while (!input.equals("n") && !input.equals("y")) {
                System.out.println("Do you want to overwrite file [y/n]?");
                input = in.next();
            }
            boolean append = input.equals("n");
            input = "";
            System.out.println("Please enter line that you want to write to file...");
            while (input.length() == 0) {
                input = in.nextLine();
            }
            String data = input;
            FileManager.write(name, data, append);
        } catch (NullPointerException | IOException ex) {
            System.err.println("Sorry, I could not find a file you were asking for :(");
        }
    }

    private boolean ifValidParams(String[] cmd) {
        switch (cmd[0]) {
            case DELETE_CMD:
            case WRITE_CMD:
            case CREATE_FILE_CMD:
            case CHANGE_DIRECTORY_CMD:
            case MAKE_DIRECTORY_CMD:
                if (cmd.length != 2) {
                    System.err.println("Your command is invalid, try another one or enter help");
                    return false;
                }
                break;
            case HELP_CMD:
            case STOP_CMD:
                if (cmd.length != 1) {
                    System.err.println("Your command is invalid, try another one or enter help");
                    return false;
                }
                break;
            case LIST_CMD:
                if (cmd.length > 2) {
                    System.err.println("Your command is invalid, try another one or enter help");
                    return false;
                }
                break;
            default:
                System.err.println("Your command is invalid, try another one or enter help");
                return false;
        }
        return true;
    }

    private void writeHelpInfo() {
        System.out.println("These are commands available: \n" +
                Controller.MAKE_DIRECTORY_CMD + " %name% - make directory\n" +
                Controller.CHANGE_DIRECTORY_CMD + " %name% or %path% - move to the directory in a current catalogue or specified path\n" +
                Controller.DELETE_CMD + " %name% or %path% - remove file or directory\n" +
                Controller.CREATE_FILE_CMD + " %name% or %path% - create file\n" +
                Controller.HELP_CMD + " - get help message\n" +
                Controller.LIST_CMD + " %path% or no parameter - list files and catalogues\n" +
                Controller.STOP_CMD + " - stop application\n");
    }

    private String getPrefix() {
        return FileManager.getCurrentPath() + "> ";
    }
}
