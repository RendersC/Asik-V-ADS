import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private final Map<V, V> edgeTo = new HashMap<>();
    private final Set<V> visited = new HashSet<>();
    private final V start;

    public BreadthFirstSearch(UnweightedGraph<V> graph, V start) {
        this.start = start;
        bfs(graph, start);
    }

    private void bfs(UnweightedGraph<V> graph, V current) {
        Queue<V> queue = new LinkedList<>();
        visited.add(current);
        queue.add(current);

        while (!queue.isEmpty()) {
            V vertex = queue.poll();
            for (V neighbor : graph.getAdjacencyList().getOrDefault(vertex, List.of())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    edgeTo.put(neighbor, vertex);
                    queue.add(neighbor);
                }
            }
        }
    }

    @Override
    public List<V> pathTo(V destination) {
        if (!visited.contains(destination)) return List.of();

        LinkedList<V> path = new LinkedList<>();
        for (V at = destination; at != null && !at.equals(start); at = edgeTo.get(at)) {
            path.addFirst(at);
        }
        path.addFirst(start);
        return path;
    }
}
