// Author: Suruchi Dhawan (1012512015)
// Module: UI + Main Program
// Description: Menu driven console interface for Friend Recommendation System

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Graph graph;
    static BFS bfs;
    static RecommendationEngine engine;

    public static void main(String[] args) {

        // DO NOT initialize until other modules are ready
        graph = null;
        bfs = null;
        engine = null;

        displayMenu();
    }

    // Display main menu and handle user input
    static void displayMenu() {

        int choice = 0;

        do {
            System.out.println("\n--- Friend Recommendation System ---");
            System.out.println("1. Add User");
            System.out.println("2. Add Friendship");
            System.out.println("3. Show Friends");
            System.out.println("4. Show Recommendations");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Enter a number.");
                scanner.next();
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    addFriendship();
                    break;
                case 3:
                    showFriends();
                    break;
                case 4:
                    showRecommendations();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);
    }

    // Add a new user to the network
    static void addUser() {

        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter user name: ");
        String name = scanner.nextLine();

        System.out.println("Feature not available yet (Graph module pending).");
    }

    // Add friendship between two users
    static void addFriendship() {

        System.out.print("Enter first user ID: ");
        int u1 = scanner.nextInt();

        System.out.print("Enter second user ID: ");
        int u2 = scanner.nextInt();

        System.out.println("Feature not available yet.");
    }

    // Display all friends of a user
    static void showFriends() {

        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();

        System.out.println("Feature not available yet.");
    }

    // Display friend recommendations for a user
    static void showRecommendations() {

        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();

        System.out.print("Enter number of recommendations: ");
        int n = scanner.nextInt();

        System.out.println("Feature not available yet (BFS/Engine pending).");
    }
}