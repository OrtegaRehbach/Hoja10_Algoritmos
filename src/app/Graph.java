package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// Graph class
public class Graph <E extends Comparable<E>> {
    
    private static int INF = 9999;
    private int currentTimeCode;
    private Map<Vertex<E>, List<Edge<E>>> adjVertices;

    public Graph() {
        this.adjVertices = new TreeMap<Vertex<E>, List<Edge<E>>>();
        this.currentTimeCode = 0;
    }

    public void addVertex(E label) {
        adjVertices.putIfAbsent(new Vertex<E>(label), new ArrayList<Edge<E>>());
    }
    
    public void removeVertex(E label) {
        Vertex<E> v = new Vertex<E>(label);
        adjVertices.values().stream().forEach(e -> {
            e.remove(new Edge<E>(v));
        });
        adjVertices.remove(v);
    }

    public void addEdge(E labelSrc, E labelDest, int weight) {
        Vertex<E> v1 = new Vertex<E>(labelSrc);
        Vertex<E> v2 = new Vertex<E>(labelDest);
        adjVertices.get(v1).add(new Edge<E>(v2, weight));
    }

    public void addEdge(E labelSrc, E labelDest, int timeNormal, int timeRain, int timeSnow, int timeStorm) {
        Vertex<E> v1 = new Vertex<E>(labelSrc);
        Vertex<E> v2 = new Vertex<E>(labelDest);
        adjVertices.get(v1).add(new Edge<E>(v2, timeNormal, timeRain, timeSnow, timeStorm));
    }

    public void removeEdge(E labelSrc, E labelDest) {
        Vertex<E> v1 = new Vertex<E>(labelSrc);
        Vertex<E> v2 = new Vertex<E>(labelDest);
        List<Edge<E>> eV1 = adjVertices.get(v1);
        if (eV1 != null)
            eV1.remove(new Edge<E>(v2));
    }

    public void setTime(int timeCode) {
        for (Vertex<E> vertex : adjVertices.keySet())
            adjVertices.get(vertex).stream().forEach(e -> e.setTime(timeCode));
        this.currentTimeCode = timeCode;
    }

    public String getCurrentTimeString() {
        switch (currentTimeCode) {
            case 0: return "Normal";
            case 1: return "Lluvia";
            case 2: return "Nieve";
            case 3: return "Tormenta";
            default: return "Normal";
        }
    }

    public int[][] getAdjacencyMatrix() {
        int[][] adjMatrix = new int[adjVertices.size()][adjVertices.size()];
        List<Vertex<E>> vertexList = new ArrayList<>();
        // LLenar lista de vértices
        for (Vertex<E> vertex : adjVertices.keySet())
            vertexList.add(vertex);
        // LLenar matriz de adyacencia
        for (int i = 0; i < vertexList.size(); i++) {
            for (int j = 0; j < vertexList.size(); j++) {
                List<Edge<E>> edges = adjVertices.get(vertexList.get(j));
                boolean foundConnection = false;
                int foundWeight = 0;
                for (Edge<E> edge : edges) {
                    if (edge.dest.label.equals(vertexList.get(i).label)) {
                        foundConnection = true;
                        foundWeight = edge.weight;
                        break;
                    }               
                }
                adjMatrix[j][i] = foundConnection ? foundWeight : INF;
                adjMatrix[j][i] = j == i ? 0 : adjMatrix[j][i]; // Si esta en la diagonal, colocar 0 en la matriz
            }
        }
        return adjMatrix;
    }

    public int[][] floydWarshall() {
        int dist[][] = getAdjacencyMatrix();
        int i, j, k;
        /* Add all vertices one by one
           to the set of intermediate
           vertices.
          ---> Before start of an iteration, we have shortest
               distances between all pairs of vertices such that
               the shortest distances consider only the vertices in
               set {0, 1, 2, .. k-1} as intermediate vertices.
          ----> After the end of an iteration, vertex no. k is added
                to the set of intermediate vertices and the set
                becomes {0, 1, 2, .. k} */
        for (k = 0; k < adjVertices.size(); k++) {
            // Pick all vertices as source one by one
            for (i = 0; i < adjVertices.size(); i++) {
                // Pick all vertices as destination for the above picked source
                for (j = 0; j < adjVertices.size(); j++) {
                    // If vertex k is on the shortest path
                    // from i to j, then update the value of
                    // dist[i][j]
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
        return dist;
    }

    public void printAdjacencyMatrix(int[][] matrix) {
        // Print Matrix
        Object[] keyArray = adjVertices.keySet().toArray();
        int rowCounter = 0;
        for (int[] row : matrix) {
            System.out.printf("v%d  [ ", rowCounter);
            for (int e : row) {
                System.out.printf("%3s ", (e == INF) ? "INF" : e);
            }
            System.out.println("]");
            rowCounter++;
        }
        System.out.println("");
        // Print Legend
        for (int i = 0; i < keyArray.length; i++) {
            System.out.printf("v%d: %s\n", i, keyArray[i].toString());
        }
    }

    public void printGraph() {
        for (Vertex<E> vertex : this.adjVertices.keySet()) {
            if (this.adjVertices.get(vertex).size() != 0) {
                for (Edge<E> edge : this.adjVertices.get(vertex)) {
                    System.out.println(String.format("'%s' is connected to '%s' with weight '%d'", vertex.label, edge.dest.label, edge.weight));
                }
            } else
                System.out.println(String.format("'%s' has no out-going connections", vertex.label));
        }
    }

    public void printAdjVertices() {
        System.out.println(adjVertices);
    }

    public Map<Vertex<E>, List<Edge<E>>> getAdjVertexMap() {
        return this.adjVertices;
    }

    // public List<Vertex<E>> getAdjVertices(E label) {
    //     return adjVertices.get(new Vertex<E>(label));
    // }
}
