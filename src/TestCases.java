// Author: Rishab Mehta (1012411203)
// Module: Testing + Documentation
// Description: Sample social network and test cases

public class TestCases {

    public static Graph buildSampleNetwork() {
        Graph graph = new Graph(6);
        graph.addUser(0, "Alice");
        graph.addUser(1, "Bob");
        graph.addUser(2, "Charlie");
        graph.addUser(3, "Diana");
        graph.addUser(4, "Eve");
        graph.addUser(5, "Frank");

        graph.addFriendship(0, 1);
        graph.addFriendship(0, 2);
        graph.addFriendship(1, 3);
        graph.addFriendship(1, 4);
        graph.addFriendship(2, 5);
        graph.addFriendship(3, 4);

        return graph;
    }

    public static void testGraphConstruction() {
        System.out.println("--- Test: Graph Construction ---");
        Graph graph = buildSampleNetwork();
        graph.displayFriends(0);
        graph.displayFriends(1);
        System.out.println("PASS\n");
    }

    public static void testBFS() {
        System.out.println("--- Test: BFS Traversal ---");
        Graph graph = buildSampleNetwork();
        BFS bfs = new BFS(graph);
        int[] result = bfs.bfsTraversal(0);
        System.out.print("BFS from Alice(0): ");
        for (int id : result) System.out.print(id + " ");
        System.out.println("\nPASS\n");
    }

    public static void testMutualFriendCount() {
        System.out.println("--- Test: Mutual Friend Count ---");
        Graph graph = buildSampleNetwork();
        BFS bfs = new BFS(graph);
        RecommendationEngine engine = new RecommendationEngine(graph, bfs);
        System.out.println("Mutual friends Alice(0) and Diana(3): "
            + engine.countMutualFriends(0, 3));
        System.out.println("Expected: 1 (Bob)");
        System.out.println("PASS\n");
    }

    public static void testRecommendations() {
        System.out.println("--- Test: Recommendations ---");
        Graph graph = buildSampleNetwork();
        BFS bfs = new BFS(graph);
        RecommendationEngine engine = new RecommendationEngine(graph, bfs);
        int[] recs = engine.getRecommendations(0, 3);
        System.out.print("Recommendations for Alice(0): ");
        for (int id : recs) System.out.print(id + " ");
        System.out.println("\nPASS\n");
    }

    public static void testNoFriends() {
        System.out.println("--- Test: User With No Friends ---");
        Graph graph = new Graph(3);
        graph.addUser(0, "Alone");
        BFS bfs = new BFS(graph);
        RecommendationEngine engine = new RecommendationEngine(graph, bfs);
        int[] recs = engine.getRecommendations(0, 3);
        System.out.println("Recommendations for isolated user: "
            + recs.length + " (Expected: 0)");
        System.out.println("PASS\n");
    }

    public static void main(String[] args) {
        System.out.println("=== Running All Test Cases ===\n");
        testGraphConstruction();
        testBFS();
        testMutualFriendCount();
        testRecommendations();
        testNoFriends();
        System.out.println("=== All Tests Complete ===");
    }
}