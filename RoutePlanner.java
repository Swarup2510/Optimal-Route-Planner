import java.util.*;

class Pair<K, V> {
    private final K key;
    private final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getFirst() {
        return key;
    }

    public V getSecond() {
        return value;
    }
}

class Graph {
    private int V;
    private int[][] adjacencyMatrix;

    public Graph(int V) {
        this.V = V;
        adjacencyMatrix = new int[V][V];
    }

    public void addEdge(int u, int v, int weight) {
        adjacencyMatrix[u][v] = weight;
        adjacencyMatrix[v][u] = weight; // Assuming undirected graph
    }

    public Pair<List<Integer>, Integer> shortestPath(int source, int destination) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        int[] prev = new int[V];
        Arrays.fill(prev, -1);

        pq.offer(new Node(source, 0));
        dist[source] = 0;

        while (!pq.isEmpty()) {
            Node currNode = pq.poll();
            int u = currNode.id;
            if (u == destination) break; // Reached destination

            for (int v = 0; v < V; v++) {
                int weight = adjacencyMatrix[u][v];
                if (weight > 0) {
                    int newDist = dist[u] + weight;
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        prev[v] = u;
                        pq.offer(new Node(v, newDist));
                    }
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        for (int at = destination; at != -1; at = prev[at]) {
            path.add(at);
        }
        Collections.reverse(path);

        return new Pair<>(path, dist[destination]);
    }

    private static class Node {
        int id;
        int cost;

        Node(int id, int cost) {
            this.id = id;
            this.cost = cost;
        }
    }
}

public class RoutePlanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the matrix (N): ");
        int N = scanner.nextInt();

        int[][] distances = new int[N][N];
        System.out.println("Enter the distances matrix:");

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                distances[i][j] = scanner.nextInt();
            }
        }

        Graph graph = new Graph(N);

        System.out.print("Enter the starting intersection (S): ");
        int S = scanner.nextInt();

        System.out.print("Enter the destination intersection (D): ");
        int D = scanner.nextInt();

      
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (distances[i][j] > 0) {
                    graph.addEdge(i, j, distances[i][j]);
                }
            }
        }

        Pair<List<Integer>, Integer> result = graph.shortestPath(S, D);
        List<Integer> path = result.getFirst();
        int distance = result.getSecond();

        System.out.println("Optimal Path: " + path);
        System.out.println("Distance: " + distance);

        scanner.close();
    }
}
