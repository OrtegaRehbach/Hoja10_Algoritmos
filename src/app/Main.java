package app;

public class Main {
    public static void main(String[] args) {
        Graph<String> graph = new Graph<String>();
        
        graph.addVertex("Vertex1");
        graph.addVertex("Vertex2");

        graph.addEdge("Vertex1", "Vertex2", 1);

        graph.printGraph();
    }
}