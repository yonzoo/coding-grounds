package dijkstra;

public class Edge {
  public final Vertex target;
  public final int weight;

  public Edge(Vertex argTarget, int argWeight) {
    target = argTarget;
    weight = argWeight;
  }
}