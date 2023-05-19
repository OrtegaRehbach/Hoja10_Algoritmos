package app;

import java.io.*;

public class FileHandler {

    public void createFile(String fileName) {
        try {
            File fileObj = new File(fileName);
            if (fileObj.createNewFile()) {
                System.out.println("File created: " + fileObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeGraphToFile(String fileName, Graph<String> graph) {
        BufferedWriter outputWriter = null;
        try {
            outputWriter = new BufferedWriter(new FileWriter(fileName));

            for (Vertex<String> vertex : graph.getAdjVertexMap().keySet()) {
                for (Edge<String> edge : graph.getAdjVertexMap().get(vertex)) {
                    outputWriter.write(String.format(
                        "%s %s %d %d %d %d", 
                        vertex.label, 
                        edge.dest.label, 
                        edge.timeNormal, 
                        edge.timeRain, 
                        edge.timeSnow,
                        edge.timeStorm
                    ));
                    outputWriter.newLine();
                }
            }
            outputWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Graph<String> readGraphFromFile(String fileName) throws IOException{
        Graph<String> graph = new Graph<>();
        String currentLine;
        try {
            BufferedReader buffReader = new BufferedReader(new FileReader(fileName));
            while ((currentLine = buffReader.readLine()) != null) {
                String[] tokens = currentLine.split(" ");
                // Parsear tokens a grafo
                graph.addVertex(tokens[0]);
                graph.addVertex(tokens[1]);
                graph.addEdge(
                    tokens[0], 
                    tokens[1], 
                    Integer.parseInt(tokens[2]), 
                    Integer.parseInt(tokens[3]),
                    Integer.parseInt(tokens[4]),
                    Integer.parseInt(tokens[5])
                );
            }
            buffReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return graph;
    }
}
