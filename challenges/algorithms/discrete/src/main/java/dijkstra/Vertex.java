package dijkstra;

public class Vertex implements Comparable<Vertex>
{
  public final int name;
  public Edge[] adjacencies;
  public int minDistance = Dijkstra.INFINITY;
  public Vertex previous;
  public Vertex(int argName) { name = argName; }
  public String toString() { return String.valueOf(name); }
  public int compareTo(Vertex other)
  {
    return Integer.compare(minDistance, other.minDistance);
  }

}
