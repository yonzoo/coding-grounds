package floid;

import java.util.Scanner;

public class Floid {
  public static int INFINITY = Integer.MAX_VALUE / 2;
  private final Integer[][] w;
  private final Integer[][] h;

  public Floid(Integer[][] w) {
    this.w = w;
    int n = w.length;
    h = new Integer[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (w[i][j] != INFINITY) {
          h[i][j] = j;
        } else {
          h[i][j] = -1;
        }
      }
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (w[i][j] != INFINITY) {
          for (int k = 0; k < n; k++) {
            if (w[i][k] > w[i][j] + w[j][k]) {
              w[i][k] = w[i][j] + w[j][k];
              h[i][k] = h[i][j];
            }
          }
        }
      }
    }

  }

  public void printShortestPath() {
    try {
      int from = 1;
      int to = 2;
      if (h[from][to] != -1) {
        System.out.print(from + 1 + "->");
        int v = h[from][to];
        while (v != to) {
          System.out.print(v + 1 + "->");
          v = h[v][to];
        }
        System.out.print(to + 1);
      } else {
        throw new IllegalArgumentException();
      }
      System.out.println();
    } catch (Exception ex) {
      System.err.println("Путь не был обнаружен");
    }
  }

  public void printWeightsMatrix() {
    for (int i = 0; i < w.length; i++) {
      for (int j = 0; j < w.length; j++) {
        if (w[i][j] != INFINITY)
          System.out.print(w[i][j] + " ");
        else System.out.print("x ");
      }
      System.out.println();
    }
  }

  public void printHistoryMatrix() {
    for (int i = 0; i < h.length; i++) {
      for (int j = 0; j < h.length; j++) {
        if (h[i][j] != -1)
          System.out.print(h[i][j] + " ");
        else System.out.print("x ");
      }
      System.out.println();
    }
  }
}
