import java.util.*;

public class WeightedGraph<V> {
    private final boolean directed;
    private final Map<V, Vertex<V>> vertices = new HashMap<>();

    public WeightedGraph(boolean directed) {
        this.directed = directed;
    }

    public void addEdge(V source, V destination, double weight) {
        Vertex<V> sourceVertex = vertices.computeIfAbsent(source, Vertex::new);
        Vertex<V> destVertex = vertices.computeIfAbsent(destination, Vertex::new);
        sourceVertex.addAdjacentVertex(destVertex, weight);

        if (!directed) {
            destVertex.addAdjacentVertex(sourceVertex, weight);
        }
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public Collection<Vertex<V>> getVertices() {
        return vertices.values();
    }
}
