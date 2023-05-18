package app;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Graph<String> graph = new Graph<String>();
        
        graph.addVertex("Vertex1");
        graph.addVertex("Vertex2");
        graph.addVertex("Vertex3");

        graph.addEdge("Vertex1", "Vertex2", 1);
        graph.addEdge("Vertex2", "Vertex3", 2);
        graph.addEdge("Vertex1", "Vertex3", 3);

        // graph.removeEdge("Vertex1", "Vertex2");
        // graph.removeVertex("Vertex2");

        graph.printGraph();
        graph.printAdjVertices();

        System.out.println("Adjacency Matrix:");
        graph.printAdjacencyMatrix();

        System.out.println("");
        System.out.println("Read graph:");
        FileHandler fh = new FileHandler();
        try {
            Graph<String> readGraph = fh.readGraphFromFile("./res/logistica.txt");
            readGraph.printGraph();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}