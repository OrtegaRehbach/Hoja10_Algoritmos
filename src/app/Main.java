package app;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static String DIVIDER = "------------------------------------------------------------------------------";
    static String filePath = "./res/logistica.txt";

    public static void main(String[] args) {
        Graph<String> graph = new Graph<String>();
        FileHandler fh = new FileHandler();
        // Leer el archivo de texto y convetirlo a un grafo
        boolean foundFile = true;
        try {
            graph = fh.readGraphFromFile(filePath);
        } catch (IOException e) {
            foundFile = false;
            System.out.println("Error al cargar el archivo '" + filePath + "'");
            e.printStackTrace();
        }
        // Mostrar menú y pedir ingreso al usuario
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit && foundFile) {
            System.out.println(
                DIVIDER + "\n" + 
                "Gestor de cadena de suministros" + "\n" + 
                "Clima actual: " + graph.getCurrentTimeString() + "\n" + 
                DIVIDER + "\n" + 
                "1. Mostrar conexiones (mostrar grafo)" + "\n" + 
                "2. Mostrar la ruta más corta entre dos ciudades" + "\n" + 
                "3. Calcular y mostra el centro del grafo" + "\n" + 
                "4. Agregar una nueva conexión (nuevo Edge)" + "\n" + 
                "5. Ingresar una interrupción de tráfico (eliminar Edge)" + "\n" + 
                "6. Cambiar el valor del clima entre un par de ciudades (cambiar peso en Edge)" + "\n" + 
                "7. Cambiar el clima actual" + "\n" + 
                "8. Salir" + "\n" + 
                DIVIDER
            );
            System.out.print("Ingrese la opción que desea ejecutar: ");
            String input = sc.nextLine();
            System.out.println(DIVIDER);
            switch (input) {
                case "1":
                    graph.printGraph();
                    break;
                case "2":
                    graph.printAdjacencyMatrix(graph.floydWarshall());
                    break;
                case "3":
                    
                    break;
                case "4":
                    addEdgeMenu(sc, graph);
                    break;
                case "5":
                    removeEdgeMenu(sc, graph);
                    break;
                case "6":
                    
                    break;
                case "7":
                    changeTimeMenu(sc, graph);
                    break;
                case "8":
                    exit = true;
                    System.out.println("Gracias por utilizar el programa");
                    System.out.println(DIVIDER);
                    break;
                default:
                    printInvalidInputString(input);
                    break;
            }
        }
        sc.close();
    }

    public static void printInvalidInputString(String input) {
        System.out.println(String.format("'%s' no es un valor válido", input));
        System.out.println("Por favor, ingrese un valor válido");
    }

    public static boolean isValidCityName(String cityName, Graph<String> graph) {
        return graph.getAdjVertexMap().get(new Vertex<String>(cityName)) != null;
    }

    public static String getCityName(String prompt, Scanner sc, Graph<String> graph) {
        String cityName = "";
        boolean validName = false;
        while (!validName) {
            System.out.print(prompt);
            cityName = sc.nextLine().trim();
            if (!isValidCityName(cityName, graph)) {
                System.out.println(String.format("No se encontró la ciudad '%s' en el grafo", cityName));
            } else {
                validName = true;
            }
        }
        return cityName;
    }

    public static int getNumber(String prompt, Scanner sc) {
        int number = 0;
        boolean validNumber = false;
        while (!validNumber) {
            System.out.print(prompt);
            try {
                String input = sc.nextLine();
                number = Integer.parseInt(input);
                validNumber = true;
            } catch (InputMismatchException e) {
                printInvalidInputString(Integer.toString(number));
            }
        }
        return number;
    }

    public static void addEdgeMenu(Scanner sc, Graph<String> graph) {
        String src = getCityName("Ingrese la ciudad de origen: ", sc, graph);
        String dest = getCityName("Ingrese la ciudad de destino: ", sc, graph);
        int weight = getNumber("Ingrese el valor del clima (peso): ", sc);
        graph.addEdge(src, dest, weight);
        System.out.println(String.format("Se ha agregado una conexión entre '%s' y '%s' con clima '%d'", src, dest, weight));
    }

    public static void removeEdgeMenu(Scanner sc, Graph<String> graph) {
        String src = getCityName("Ingrese la ciudad de origen: ", sc, graph);
        String dest = getCityName("Ingrese la ciudad de destino: ", sc, graph);
        graph.removeEdge(src, dest);
        System.out.println(String.format("Se ha eliminado la conexión (si existe) entre '%s' y '%s'", src, dest));
    }

    public static void modifyEdgeWeightMenu(Scanner sc, Graph<String> graph) {
        String src = getCityName("Ingrese la ciudad de origen: ", sc, graph);
        String dest = getCityName("Ingrese la ciudad de destino: ", sc, graph);
        int timeNormal = getNumber("Ingrese el valor del clima (peso) normal: ", sc);
        int timeRain = getNumber("Ingrese el valor del clima (peso) lluvia: ", sc);
        int timeSnow = getNumber("Ingrese el valor del clima (peso) nieve: ", sc);
        int timeStorm = getNumber("Ingrese el valor del clima (peso) tormenta: ", sc);
        graph.removeEdge(src, dest);
        graph.addEdge(src, dest, timeNormal, timeRain, timeSnow, timeStorm);
    }

    public static void changeTimeMenu(Scanner sc, Graph<String> graph) {
        boolean exit = false;
        while (!exit) {
            System.out.println(
                "Cambiar el clima actual" + "\n" +
                DIVIDER + "\n" +  
                "1. Normal" + "\n" + 
                "2. Lluvia" + "\n" + 
                "3. Nieve" + "\n" + 
                "4. Tormenta" + "\n" + 
                "5. Regresar al menu" + "\n" + 
                DIVIDER
            );
            System.out.print("Ingrese la opción que desea ejecutar: ");
            String input = sc.nextLine();
            System.out.println(DIVIDER);
            switch (input) {
                case "1":
                    graph.setTime(0);
                    System.out.println(String.format("Se ha cambiado el clima actual a '%s'", graph.getCurrentTimeString()));
                    exit = true;
                    break;
                case "2":
                    graph.setTime(1);
                    System.out.println(String.format("Se ha cambiado el clima actual a '%s'", graph.getCurrentTimeString()));
                    exit = true;
                    break;
                case "3":
                    graph.setTime(2);
                    System.out.println(String.format("Se ha cambiado el clima actual a '%s'", graph.getCurrentTimeString()));
                    exit = true;
                    break;
                case "4":
                    graph.setTime(3);
                    System.out.println(String.format("Se ha cambiado el clima actual a '%s'", graph.getCurrentTimeString()));
                    exit = true;
                    break;
                case "5":
                    exit = true;
                    break;
                default:
                    printInvalidInputString(input);
                    System.out.println(DIVIDER);
                    break;
            }
        }
    }
}