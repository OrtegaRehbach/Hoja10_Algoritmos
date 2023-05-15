package app;

// Class to store edges of the weighted graph
public class Edge <E extends Comparable<E>> implements Comparable<Edge <E>>{

    Vertex<E> dest;
    int weight;

    Edge(Vertex<E> dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }

    Edge(Vertex<E> dest) {
        this.dest = dest;
        this.weight = 0;
    }

    @Override
    public int compareTo(Edge<E> o) {
        return dest.compareTo(o.dest);
    }

    @Override
    public boolean equals(Object obj) {
        try {
            return this.compareTo((Edge<E>) obj) == 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Edge [dest=" + dest.label + ", weight=" + weight + "]";
    }
}
