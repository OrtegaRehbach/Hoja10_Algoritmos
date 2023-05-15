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

    public void addEdge(E label1, E label2, int weight) {
        Vertex<E> v1 = new Vertex<E>(label1);
        Vertex<E> v2 = new Vertex<E>(label2);
        adjVertices.get(v1).add(new Edge<E>(v2, weight));
    }

    public void removeEdge(E label1, E label2) {
        Vertex<E> v1 = new Vertex<E>(label1);
        Vertex<E> v2 = new Vertex<E>(label2);
        List<Edge<E>> eV1 = adjVertices.get(v1);
        // List<Vertex<E>> eV2 = adjVertices.get(v2);
        if (eV1 != null)
            eV1.remove(new Edge<E>(v2));
        // if (eV2 != null)
        //     eV2.remove(v1);
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
