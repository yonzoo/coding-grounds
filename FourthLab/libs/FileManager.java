package FourthLab.libs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static Path path;

    static {
        path = Paths.get(".").toAbsolutePath().normalize();
    }

    public static void writeToFile(String name, String data, boolean appendable) throws IOException, NullPointerException {
        File file = new File(path.resolve(name).toString());
        if (file.exists())
            new FileWriter(file, appendable).write(data);
    }

    public static List<Path> ls() throws IOException, NullPointerException {
        List<Path> result = new ArrayList<>();
        Files.list(path).forEach(result::add);
        return result;
    }

    public static List<Path> ls(Path path) throws IOException, NullPointerException {
        List<Path> result = new ArrayList<>();
        Files.list(path).forEach(result::add);
        return result;
    }

    public static write(path: Path) {
        System.out.println("Please write a string below to write to file");
        String s = ""
        BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
         while (s.length == 0) {
             s = scn.nextLine();
         }
         writer.append('\n' + s);
         writer.close();
    }

    public static void cd(String name) throws NullPointerException {
        if (name.equals(".")) return;
        if (name.equals("..")) {
            if (path.getParent() == null) return;
            path = path.getParent();
        } else {
            cd(path.resolve(name));
        }
    }

    public static void cd(Path newPath) throws NullPointerException {
        File f = new File(newPath.toString());
        if (f.exists() && f.isDirectory()) path = newPath;
        else throw new NullPointerException();
    }

    public static void rm(String name) throws NullPointerException, IOException {
        rm(path.resolve(name));
    }

    public static void rm(Path path) throws IOException, NullPointerException {
        Files.delete(path);
    }

    public static void touch(String name) throws IOException, NullPointerException {
        touch(path.resolve(name));
    }


    public static void touch(Path path) throws IOException {
        Files.createFile(path);
    }

    public static void write(String name, String data, boolean append) throws IOException, NullPointerException {
        write(path.resolve(name), data, append);
    }

    public static void write(Path path, String data, boolean append) throws IOException, NullPointerException {
        File f = new File(path.toString());
        if (f.exists() && f.isFile()) {
            FileWriter fw = new FileWriter(f, append);
            fw.write(data);
            fw.close();
        } else throw new NullPointerException();
    }

    public static void mkdir(String name) throws IOException {
        mkdir(path.resolve(name));
    }

    public static void mkdir(Path path) throws IOException {
        Files.createDirectory(path);
    }

    public static Path getCurrentPath() {
        return path;
    }
}
