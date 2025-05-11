import java.util.*;

public class UnweightedGraph<V> {
    private final boolean directed;
    private final Map<V, List<V>> adjacencyList = new HashMap<>();

    public UnweightedGraph(boolean directed) {
        this.directed = directed;
    }

    public void addEdge(V source, V destination) {
        adjacencyList.computeIfAbsent(source, k -> new ArrayList<>()).add(destination);
        if (!directed) {
            adjacencyList.computeIfAbsent(destination, k -> new ArrayList<>()).add(source);
        }
    }

    public Map<V, List<V>> getAdjacencyList() {
        return adjacencyList;
    }
}
