package app;

// Class to store edges of the weighted graph
public class Edge <E extends Comparable<E>> implements Comparable<Edge <E>>{

    Vertex<E> dest;
    int weight;
    int timeNormal, timeRain, timeSnow, timeStorm;

    public Edge(Vertex<E> dest, int weight) {
        this.dest = dest;
        this.weight = weight;
        this.timeNormal = weight;
        this.timeRain = weight;
        this.timeSnow = weight;
        this.timeStorm = weight;
    }

    public Edge(Vertex<E> dest) {
        this.dest = dest;
        this.weight = 0;
        this.timeNormal = weight;
        this.timeRain = weight;
        this.timeSnow = weight;
        this.timeStorm = weight;
    }

    public Edge(Vertex<E> dest, int timeNormal, int timeRain, int timeSnow, int timeStorm) {
        this.dest = dest;
        this.timeNormal = timeNormal;
        this.timeRain = timeRain;
        this.timeSnow = timeSnow;
        this.timeStorm = timeStorm;
        this.weight = timeNormal;
    }

    public void setTime(int timeCode) {
        switch (timeCode) {
            case 0:
                this.weight = this.timeNormal;
                break;
            case 1:
                this.weight = this.timeRain;
                break;
            case 2:
                this.weight = this.timeSnow;
                break;
            case 3:
                this.weight = this.timeStorm;
                break;
            default:
                this.weight = timeNormal;
                break;
        }
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
