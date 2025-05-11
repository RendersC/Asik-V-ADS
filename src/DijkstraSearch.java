import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private final Map<V, V> edgeTo = new HashMap<>();
    private final Map<V, Double> distTo = new HashMap<>();
    private final Set<V> visited = new HashSet<>();
    private final V start;

    public DijkstraSearch(WeightedGraph<V> graph, V start) {
        this.start = start;

        PriorityQueue<Vertex<V>> pq = new PriorityQueue<>(Comparator.comparingDouble(v -> distTo.getOrDefault(v.getData(), Double.POSITIVE_INFINITY)));

        Vertex<V> startVertex = graph.getVertex(start);
        distTo.put(start, 0.0);
        pq.add(startVertex);

        while (!pq.isEmpty()) {
            Vertex<V> current = pq.poll();
            V currentData = current.getData();

            if (visited.contains(currentData)) continue;
            visited.add(currentData);

            for (Map.Entry<Vertex<V>, Double> neighborEntry : current.getAdjacentVertices().entrySet()) {
                V neighborData = neighborEntry.getKey().getData();
                double weight = neighborEntry.getValue();
                double newDist = distTo.get(currentData) + weight;

                if (newDist < distTo.getOrDefault(neighborData, Double.POSITIVE_INFINITY)) {
                    distTo.put(neighborData, newDist);
                    edgeTo.put(neighborData, currentData);
                    pq.add(neighborEntry.getKey());
                }
            }
        }
    }

    @Override
    public List<V> pathTo(V destination) {
        if (!distTo.containsKey(destination)) return List.of();

        LinkedList<V> path = new LinkedList<>();
        for (V at = destination; at != null && !at.equals(start); at = edgeTo.get(at)) {
            path.addFirst(at);
        }
        path.addFirst(start);
        return path;
    }
}
