package prim;

import java.lang.*;

public class MST {
  //Количество вершин
  private static final int V = 8;
  public static int INFINITY = Integer.MAX_VALUE / 2;

  int minKey(int[] key, Boolean[] mstSet) {
    int min = INFINITY, min_index = -1;

    for (int v = 0; v < V; v++)
      if (!mstSet[v] && key[v] < min) {
        min = key[v];
        min_index = v;
      }

    return min_index;
  }

  void printMST(int[] parent, Integer[][] graph) {
    System.out.println("Ребро \tВес");
    for (int i = 1; i < V; i++)
      System.out.println((parent[i] + 1)+ " - " + (i + 1) + "\t" + graph[i][parent[i]]);
  }

  public void primMST(Integer[][] graph) {
    int[] parent = new int[V];
    int[] key = new int[V];
    Boolean[] mstSet = new Boolean[V];

    for (int i = 0; i < V; i++) {
      key[i] = INFINITY;
      mstSet[i] = false;
    }

    key[0] = 0;
    parent[0] = -1;

    for (int count = 0; count < V - 1; count++) {
      int u = minKey(key, mstSet);
      mstSet[u] = true;
      for (int v = 0; v < V; v++)
        if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
          parent[v] = u;
          key[v] = graph[u][v];
        }
    }

    printMST(parent, graph);
  }
}