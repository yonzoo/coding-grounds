package dijkstra;

import java.util.*;

public class Dijkstra {
  public static int INFINITY = Integer.MAX_VALUE / 2;

  public static void computePaths(Vertex source) {
    source.minDistance = 0;
    PriorityQueue<Vertex> vertexQueue = new PriorityQueue<>();
    vertexQueue.add(source);

    while (!vertexQueue.isEmpty()) {
      Vertex u = vertexQueue.poll();

      for (Edge e : u.adjacencies) {
        Vertex v = e.target;
        int weight = e.weight;
        int distanceThroughU = u.minDistance + weight;
        if (distanceThroughU < v.minDistance) {
          vertexQueue.remove(v);

          v.minDistance = distanceThroughU;
          v.previous = u;
          vertexQueue.add(v);
        }
      }
    }
  }

  public static List<Vertex> getShortestPathTo(Vertex target)
  {
    List<Vertex> path = new ArrayList<>();
    for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
      path.add(vertex);

    Collections.reverse(path);
    return path;
  }
}
