import dijkstra.Dijkstra;
import dijkstra.Edge;
import dijkstra.Vertex;
import floid.Floid;
import prim.MST;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) {
    try {
      System.out.println("\nАлгоритм Флойда-Уоршалла\n------------------");
      floid();
      System.out.println("\nАлгоритм Дейкстры\n------------------");
      dijkstra();
      System.out.println("\nАлгоритм Прима\n------------------");
      prim();
    } catch (IOException ex) {
      System.err.println("Произошла ошибка при чтении файла");
    }
  }

  public static void floid() throws IOException {
    Integer[][] w = Parser.parse();
    Floid floid = new Floid(w);
    System.out.println("Матрица весов: ");
    floid.printWeightsMatrix();
    System.out.println();
    System.out.println("Матрица историй: ");
    floid.printHistoryMatrix();
    System.out.println();
    System.out.println("Самый короткий путь из вершины 2 в вершину 3: ");
    floid.printShortestPath();
  }

  public static void dijkstra() throws IOException {

    Integer[][] w = Parser.parse();
    var vertices = new Vertex[w.length];
    for (int i = 0; i < w.length; i++) {
      vertices[i] = new Vertex(i + 1);
    }

    for (int i = 0; i < w.length; i++) {
      List<Edge> adjenciesList = new ArrayList<>();
      for (int j = 0; j < w.length; j++) {
        adjenciesList.add(new Edge(vertices[j], w[i][j]));
      }
      Edge[] adjenciesArray = new Edge[adjenciesList.size()];
      adjenciesList.toArray(adjenciesArray);
      vertices[i].adjacencies = adjenciesArray;
    }

    Dijkstra.computePaths(vertices[1]);
    System.out.println("Вес самого короткого пути из вершины 2 в вершину 3:");
    System.out.println(vertices[2].minDistance + "\n");
    List<Vertex> path = Dijkstra.getShortestPathTo(vertices[2]);
    System.out.println("Самый короткий путь из вершины 2 в вершину 3: ");
    System.out.println(path.stream().map(Vertex::toString).collect(Collectors.joining("->")));
  }

  public static void prim() throws IOException {
    Integer[][] w = Parser.parse();
    MST t = new MST();
    System.out.println("Минимальный остов: ");
    t.primMST(w);
  }
}
