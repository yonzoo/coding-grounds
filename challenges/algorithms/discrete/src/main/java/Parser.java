import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {
  public static int INFINITY = Integer.MAX_VALUE / 2;
  public static Integer[][] parse() throws IOException {
    var args = read();
    var maxN = Math.max(getMaxN(args), getMaxM(args));
    var result = new Integer[maxN][maxN];
    for (int i = 0; i < maxN; i++) {
      for (int j = 0; j < maxN; j++) {
        if (result[i][j] == null && result[j][i] == null) {
          var weight = getWeightForCoordinates(i, j, args);
          result[i][j] = weight;
          result[j][i] = weight;
        }
      }
    }

    return result;
  }

  public static List<String> read() throws IOException {
    File file = new File(Path.of("src", "main", "resources", "source.txt").toUri());
    BufferedReader br = new BufferedReader(new FileReader(file));

    List<String> args = new ArrayList<>();
    String st;
    while ((st = br.readLine()) != null) {
      args.add(st);
    }
    return args;
  }

  private static Integer getWeightForCoordinates(Integer i, Integer j, List<String> args) {
    var element = args
        .stream()
        .filter(arg -> (Integer.parseInt(String.valueOf(arg.charAt(4))) - 1 == i) && (Integer.parseInt(String.valueOf(arg.charAt(7))) - 1 == j))
        .findFirst();
    return element.map(s -> Integer.parseInt(s.split("=")[1])).orElse(INFINITY);
  }


  private static int getMaxN(List<String> args) {
    return Collections.max(args.stream().map(arg -> Integer.parseInt(String.valueOf(arg.charAt(4)))).collect(Collectors.toList()));
  }

  private static int getMaxM(List<String> args) {
    return Collections.max(args.stream().map(arg -> Integer.parseInt(String.valueOf(arg.charAt(7)))).collect(Collectors.toList()));
  }
}
