/**
 * An implementation of the edge object structure
 */
package graph;

public class Edge {
    private int source;
    private int destination;
    private double weight;

    public Edge(int source, int destination, double weight){
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public int getSource() {
        return source;
    }

    public int getDestination() {
        return destination;
    }

    public double getWeight() {
        return weight;
    }
}
