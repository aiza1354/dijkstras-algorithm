import java.util.*;
public class Dijkstra {
    // Inner class to represent an edge in the graph
    static class Edge {
        int destination;
        int weight;
        String name;

        public Edge(int destination, int weight, String name) {
            this.destination = destination;
            this.weight = weight;
            this.name = name;
        }
    }

    // Inner class to represent a node with its current distance
    // Used in the priority queue
    static class Node implements Comparable<Node> {
        int vertex;
        int distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        // Compare nodes by distance (for the priority queue)
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static int[] dijkstra(List<List<Edge>> graph, int source) {
        int n = graph.size();
        // Array to store shortest distance from source to each vertex
        int[] distances = new int[n];

        // Array to track which vertices we've visited
        boolean[] visited = new boolean[n];

        // Initialize all distances as "infinity" (max value)
        Arrays.fill(distances, Integer.MAX_VALUE);

        // Distance from source to itself is 0
        distances[source] = 0;

        // Priority queue to always process the nearest unvisited vertex
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(source, 0));

        while (!pq.isEmpty()) {
            // Get the vertex with minimum distance
            Node current = pq.poll();
            int u = current.vertex;

            // Skip if we've already processed this vertex
            if (visited[u]) continue;
            // Mark this vertex as visited
            visited[u] = true;
            // Check all neighbours of current vertex
            for (Edge edge : graph.get(u)) {
                int v = edge.destination;
                int weight = edge.weight;
                // If we've found a shorter path to vertex v
                if (!visited[v] && distances[u] + weight < distances[v]) {
                    // Update the distance
                    System.out.println("Value of " + distances[v]);
                    distances[v] = distances[u] + weight;
                    System.out.println("Value of " + distances[v] + " replaced.");


                    // Add to priority queue with new distance
                    pq.offer(new Node(v, distances[v]));
                }
            }
        }
        return distances;
    }

    // Helper method to print the results clearly
    public static void printDistances(int[] distances, int source) {
        System.out.println("Shortest distances from vertex " + source + ":");
        for (int i = 0; i < distances.length; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                System.out.println("To vertex " + i + ": unreachable");
            } else {
                System.out.println("To vertex " + i + ": " + distances[i]);
            }
        }
    }

    // Example usage
    public static void main(String[] args) {
        // Create a graph with 5 vertices (0-4)
        int numVertices = 22;
        List<List<Edge>> graph = new ArrayList<>();

        // Initialize adjacency list
        for (int i = 0; i < numVertices; i++) {
            graph.add(new ArrayList<>());
        }
        // Add graphs
        // From Hardcastle Hall
        graph.get(0).add(new Edge(1, 3, "Science"));
        graph.get(0).add(new Edge(2, 3, "Staff room"));
        graph.get(0).add(new Edge(3, 3, "PAT"));
        graph.get(0).add(new Edge(6, 2, "PE"));
        // from science
        graph.get(1).add(new Edge(0, 3, "Hardcastle Hall"));
        graph.get(1).add(new Edge(10, 5, "Geogaphy"));
        graph.get(1).add(new Edge(11, 5, "LSDH"));
        // from the staff Room
        graph.get(2).add(new Edge(0, 3, "Hardcastle Hall"));
        graph.get(2).add(new Edge(4, 4, "Gym"));
        // from Pat
        graph.get(3).add(new Edge(0, 3, "Hardcastle hall"));
        graph.get(3).add(new Edge(8, 2, "History"));
        // from gym
        graph.get(4).add(new Edge(2, 4, "Staff room"));
        graph.get(4).add(new Edge(5, 3, "Food tech"));
        // from food tech
        graph.get(5).add(new Edge(4, 3, "Gym"));
        graph.get(5).add(new Edge(6, 1, "PE"));
        graph.get(5).add(new Edge(7, 3, "Real gym"));
        // from PE
        graph.get(6).add(new Edge(0, 2, "Hardcastle Hall"));
        graph.get(6).add(new Edge(5, 1, "Food Tech"));
        graph.get(6).add(new Edge(7, 3, "Real gym"));
        // From the Real gym
        graph.get(7).add(new Edge(5, 3, "Food Tech"));
        graph.get(7).add(new Edge(6, 3, "PE"));
        // From history
        graph.get(8).add(new Edge(3, 2, "PAT"));
        graph.get(8).add(new Edge(9, 4, "Art"));
        // from Art
        graph.get(9).add(new Edge(8, 4, "History"));
        // from Geography
        graph.get(10).add(new Edge(1, 5, "Science"));
        graph.get(10).add(new Edge(15, 5, "Drama"));
        graph.get(10).add(new Edge(16, 6, "Lecture theatre"));
        //from LSDH
        graph.get(11).add(new Edge(1, 5, "Science"));
        graph.get(11).add(new Edge(12, 1, "RH"));
        // from RH
        graph.get(12).add(new Edge(11, 1, "LSDH"));
        graph.get(12).add(new Edge(13, 4, "SF"));
        // from SF
        graph.get(13).add(new Edge(12, 4, "RH"));
        graph.get(13).add(new Edge(14, 1, "USDH"));
        graph.get(13).add(new Edge(15, 2, "Drama"));
        graph.get(13).add(new Edge(17, 3, "Library"));
        // FROM USDH
        graph.get(14).add(new Edge(13, 1, "SF"));
        // Fom Drama
        graph.get(15).add(new Edge(10, 5, "Geography"));
        graph.get(15).add(new Edge(13, 2, "SF"));
        graph.get(15).add(new Edge(16, 1, "Lecture theatre"));
        // From Lecture theatre
        graph.get(16).add(new Edge(10, 6, "Geography"));
        graph.get(16).add(new Edge(15, 1, "Drama"));
        // From library
        graph.get(17).add(new Edge(13, 3, "SF"));
        graph.get(17).add(new Edge(18, 3, "English"));
        // From English
        graph.get(18).add(new Edge(17, 3, "Library"));
        graph.get(18).add(new Edge(19, 2, "Music"));
        graph.get(18).add(new Edge(21, 5, "Language"));
        // From Music
        graph.get(19).add(new Edge(18, 2, "English"));
        graph.get(19).add(new Edge(20, 4, "C.S"));
        // From Computer science
        graph.get(20).add(new Edge(19, 4, "Music"));
        graph.get(20).add(new Edge(21, 2, "Language"));
        // From Language
        graph.get(21).add(new Edge(20, 2, "C.S"));
        graph.get(21).add(new Edge(18, 5, "English"));

        int[] shortestDistances = dijkstra(graph, 0);
        printDistances(shortestDistances, 0);
    }
}
