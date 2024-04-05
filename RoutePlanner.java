import java.util.*;

class Graph {
    private int V;
    private int[][] originalMatrix; 
    private int[][] currentMatrix; 

    public Graph(int V) {
        this.V = V;
        originalMatrix = new int[V][V];
        currentMatrix = new int[V][V];
    }

    public void addEdge(int u, int v, int weight) {
        originalMatrix[u][v] = weight;
        originalMatrix[v][u] = weight; 
        currentMatrix[u][v] = weight;
        currentMatrix[v][u] = weight;
    }

    public void updateTraffic(int[][] trafficUpdates) {
        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                if (trafficUpdates[i][j] != 0) {
                    currentMatrix[i][j] = originalMatrix[i][j] + trafficUpdates[i][j];
                    currentMatrix[j][i] = originalMatrix[j][i] + trafficUpdates[j][i];
                }
            }
        }
    }

    public void shortestPath(int source, int destination) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.time));
        int[] time = new int[V];
        Arrays.fill(time, Integer.MAX_VALUE);
        int[] prev = new int[V];
        Arrays.fill(prev, -1);
    
        pq.offer(new Node(source, 0));
        time[source] = 0;
    
        while (!pq.isEmpty()) {
            Node currNode = pq.poll();
            int u = currNode.id;
            if (u == destination)    
            {         
                break;
            }
            
    
            for (int j = 0; j< V; j++) {
                int weight = currentMatrix[u][j];
                if (weight > 0) {
                    int newTime = time[u] + weight;
                    if (newTime < time[j]) {
                        time[j] = newTime;
                        prev[j] = u;
                        pq.offer(new Node(j, newTime));
                    }
                }
            }
        }
    
        List<Integer> path = new ArrayList<>();
        for (int i = destination; i != -1; i = prev[i]) {
            path.add(i);
        }
        Collections.reverse(path);
    
        System.out.println("Optimal Path: " + path);
        System.out.println("Time: " + time[destination] + " minutes");
    }
    
    private static class Node {
        int id;
        int time;
    
        Node(int id, int time) {
            this.id = id;
            this.time = time;
        }
    }
}

public class RoutePlanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the matrix (N): ");
        int N = scanner.nextInt();

        Graph graph = new Graph(N);

        System.out.println("Enter the matrix:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph.addEdge(i, j, scanner.nextInt());
            }
        }

        System.out.print("Enter the starting intersection (S): ");
        int S = scanner.nextInt();

        System.out.print("Enter the destination intersection (D): ");
        int D = scanner.nextInt();

        graph.shortestPath(S, D);

        System.out.print("Enter the number of traffic updates: ");
        int numUpdates = scanner.nextInt();

        int[][] trafficUpdates = new int[N][N];
        System.out.println("Enter the traffic updates matrix:");
        for (int i = 0; i < numUpdates; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int traffic = scanner.nextInt();
            trafficUpdates[from][to] = traffic;
        }

        graph.updateTraffic(trafficUpdates);
        System.out.println("\nAfter traffic updates:");
        graph.shortestPath(S, D);

        scanner.close();
    }
}
