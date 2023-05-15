package app;

public class Vertex <E extends Comparable<E>> implements Comparable<Vertex <E>> {
    public E label;

    public Vertex(E label) {
        this.label = label;
    }

    @Override
    public int compareTo(Vertex<E> o) {
        return this.label.compareTo(o.label);
    }
}
