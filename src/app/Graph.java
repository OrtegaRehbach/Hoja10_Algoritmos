package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// Graph class
public class Graph <E extends Comparable<E>> {
    
    private Map<Vertex<E>, List<Edge<E>>> adjVertices;

    public Graph() {
        this.adjVertices = new TreeMap<Vertex<E>, List<Edge<E>>>();
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
                adjMatrix[j][i] = foundConnection ? foundWeight : 999;
            }
        }
        return adjMatrix;
    }

    public void printAdjacencyMatrix() {
        for (int[] row : getAdjacencyMatrix()) {
            System.out.print("[ ");
            for (int e : row) {
                System.out.printf("%3d ", e);
            }
            System.out.println("]");
        }
    }

    public void printGraph() {
        for (Vertex<E> vertex : this.adjVertices.keySet()) {
            for (Edge<E> edge : this.adjVertices.get(vertex)) {
                System.out.println(String.format("'%s' is connected to '%s' with weight '%d'", vertex.label, edge.dest.label, edge.weight));
            }
        }
    }

    public void printAdjVertices() {
        System.out.println(adjVertices);
    }

    // public List<Vertex<E>> getAdjVertices(E label) {
    //     return adjVertices.get(new Vertex<E>(label));
    // }
}
