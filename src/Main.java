// Author: Suruchi Dhawan (1012512015)
// Module: UI + Main Program
// Description: Menu driven console interface for Friend Recommendation System

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Graph graph;
    static BFS bfs;
    static RecommendationEngine engine;
    static int userCount = 0;
    static final int MAX_USERS = 50;

    public static void main(String[] args) {
        graph = new Graph(MAX_USERS);
        bfs = new BFS(graph);
        engine = new RecommendationEngine(graph, bfs);

        System.out.println("=== Friend Recommendation System ===");
        System.out.println("Using BFS and Mutual Friend Count");
        System.out.println("=====================================\n");

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1: addUser(); break;
                case 2: addFriendship(); break;
                case 3: showFriends(); break;
                case 4: showRecommendations(); break;
                case 5: showBFSTraversal(); break;
                case 6:
                    System.out.println("Exiting. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    static void displayMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Add User");
        System.out.println("2. Add Friendship");
        System.out.println("3. Show Friends of a User");
        System.out.println("4. Get Friend Recommendations");
        System.out.println("5. BFS Traversal");
        System.out.println("6. Exit");
        System.out.print("Enter choice: ");
    }

    static void addUser() {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine().trim();
        graph.addUser(userCount, name);
        System.out.println("User added: " + name + " (ID: " + userCount + ")");
        userCount++;
    }

    static void addFriendship() {
        System.out.print("Enter first user ID: ");
        int id1 = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Enter second user ID: ");
        int id2 = Integer.parseInt(scanner.nextLine().trim());
        graph.addFriendship(id1, id2);
        System.out.println("Friendship added between User " + id1 + " and User " + id2);
    }

    static void showFriends() {
        System.out.print("Enter user ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        graph.displayFriends(id);
    }

    static void showRecommendations() {
        System.out.print("Enter user ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("How many recommendations? ");
        int n = Integer.parseInt(scanner.nextLine().trim());
        int[] recs = engine.getRecommendations(id, n);
        if (recs.length == 0) {
            System.out.println("No recommendations found.");
            return;
        }
        System.out.println("Top " + n + " recommendations for User " + id + ":");
        for (int i = 0; i < recs.length; i++) {
            System.out.println("  " + (i+1) + ". User " + recs[i]
                + " - Mutual friends: "
                + engine.countMutualFriends(id, recs[i]));
        }
    }

    static void showBFSTraversal() {
        System.out.print("Enter source user ID: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        int[] visited = bfs.bfsTraversal(id);
        System.out.print("BFS order from User " + id + ": ");
        for (int i = 0; i < visited.length; i++) {
            System.out.print(visited[i] + " ");
        }
        System.out.println();
    }
}
