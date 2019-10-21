package properties;

import java.io.*;
import java.util.Properties;

public class PropertiesReader {
    private static Properties props = new Properties();

    public static void readProps() {
        try (InputStream input = new FileInputStream("./properties")) {
            props.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void printProps() {
        for (String key : props.stringPropertyNames()) {
            System.out.println(key + ":" + props.get(key));
        }
    }
}
