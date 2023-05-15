package app;

// Class to store edges of the weighted graph
public class Edge <E extends Comparable<E>> implements Comparable<Edge <E>>{

    Vertex<E> dest;
    int weight;

    Edge(Vertex<E> dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge<E> o) {
        return dest.compareTo(o.dest);
    }
}
