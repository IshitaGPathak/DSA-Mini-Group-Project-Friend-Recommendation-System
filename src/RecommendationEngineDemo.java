// Author: Ishita Pathak (1012411228)
// Module: Recommendation Engine - Standalone Demo
// Course: Data Structures 2
// College: DES Pune University

public class RecommendationEngineDemo {

    // Manual linked list node for adjacency list
    static class Node {
        int userId;
        Node next;
        Node(int userId) {
            this.userId = userId;
            this.next = null;
        }
    }

    // Simple graph using array of linked lists
    static Node[] adjList;
    static int totalUsers = 6;

    static void addFriendship(int u, int v) {
        // Add v to u's list
        Node newNode1 = new Node(v);
        newNode1.next = adjList[u];
        adjList[u] = newNode1;

        // Add u to v's list (undirected)
        Node newNode2 = new Node(u);
        newNode2.next = adjList[v];
        adjList[v] = newNode2;
    }

    static Node getFriends(int userId) {
        return adjList[userId];
    }

    // Count mutual friends between source and candidate
    // Time Complexity: O(D^2) where D = average degree
    static int countMutualFriends(int sourceId, int candidateId) {
        int mutualCount = 0;

        Node sourceFriend = getFriends(sourceId);

        while (sourceFriend != null) {
            Node candidateFriend = getFriends(candidateId);

            while (candidateFriend != null) {
                if (sourceFriend.userId == candidateFriend.userId) {
                    mutualCount++;
                    break;
                }
                candidateFriend = candidateFriend.next;
            }
            sourceFriend = sourceFriend.next;
        }
        return mutualCount;
    }

    // Sort candidates by mutual count using insertion sort
    // Time Complexity: O(R^2) where R = number of candidates
    static void sortByMutualCount(int[] candidates, int[] mutualCounts) {
        int n = candidates.length;

        for (int i = 1; i < n; i++) {
            int keyCount = mutualCounts[i];
            int keyCandidate = candidates[i];
            int j = i - 1;

            while (j >= 0 && mutualCounts[j] < keyCount) {
                mutualCounts[j + 1] = mutualCounts[j];
                candidates[j + 1] = candidates[j];
                j--;
            }

            mutualCounts[j + 1] = keyCount;
            candidates[j + 1] = keyCandidate;
        }
    }

    // Get top N recommendations for source user
    static int[] getRecommendations(int sourceId, int topN) {
        // Step 1 - find friends of friends manually
        boolean[] isFriend = new boolean[totalUsers];
        boolean[] visited = new boolean[totalUsers];

        // Mark direct friends
        Node f = getFriends(sourceId);
        while (f != null) {
            isFriend[f.userId] = true;
            f = f.next;
        }

        // Find candidates - friends of friends
        int[] candidates = new int[totalUsers];
        int[] mutualCounts = new int[totalUsers];
        int count = 0;

        Node friend = getFriends(sourceId);
        while (friend != null) {
            Node fof = getFriends(friend.userId);
            while (fof != null) {
                // Not self, not direct friend, not already added
                if (fof.userId != sourceId && !isFriend[fof.userId] 
                    && !visited[fof.userId]) {
                    candidates[count] = fof.userId;
                    mutualCounts[count] = countMutualFriends(sourceId, fof.userId);
                    visited[fof.userId] = true;
                    count++;
                }
                fof = fof.next;
            }
            friend = friend.next;
        }

        // Step 2 - sort by mutual count
        int[] trimmedCandidates = new int[count];
        int[] trimmedCounts = new int[count];
        for (int i = 0; i < count; i++) {
            trimmedCandidates[i] = candidates[i];
            trimmedCounts[i] = mutualCounts[i];
        }
        sortByMutualCount(trimmedCandidates, trimmedCounts);

        // Step 3 - return top N
        int resultSize = Math.min(topN, count);
        int[] result = new int[resultSize];
        for (int i = 0; i < resultSize; i++) {
            result[i] = trimmedCandidates[i];
        }
        return result;
    }

    public static void main(String[] args) {

        // User mapping:
        // 0=Alice, 1=Bob, 2=Charlie, 3=Diana, 4=Eve, 5=Frank

        adjList = new Node[totalUsers];

        // Build social network
        addFriendship(0, 1); // Alice - Bob
        addFriendship(0, 2); // Alice - Charlie
        addFriendship(1, 3); // Bob - Diana
        addFriendship(1, 4); // Bob - Eve
        addFriendship(2, 5); // Charlie - Frank
        addFriendship(3, 4); // Diana - Eve

        System.out.println("=== Friend Recommendation System ===");
        System.out.println("Module: Recommendation Engine");
        System.out.println("Author: Ishita Pathak (1012411228)");
        System.out.println("=====================================\n");

        System.out.println("Social Network:");
        System.out.println("Alice(0) -- Bob(1), Charlie(2)");
        System.out.println("Bob(1)   -- Diana(3), Eve(4)");
        System.out.println("Charlie(2) -- Frank(5)");
        System.out.println("Diana(3) -- Eve(4)\n");

        // Test countMutualFriends
        System.out.println("--- Testing countMutualFriends ---");
        System.out.println("Mutual friends between Alice(0) and Diana(3): "
            + countMutualFriends(0, 3));
        System.out.println("Mutual friends between Alice(0) and Eve(4):   "
            + countMutualFriends(0, 4));
        System.out.println("Mutual friends between Alice(0) and Frank(5): "
            + countMutualFriends(0, 5));

        // Test sortByMutualCount
        System.out.println("\n--- Testing sortByMutualCount ---");
        int[] testCandidates = {3, 4, 5};
        int[] testCounts = {1, 1, 1};
        sortByMutualCount(testCandidates, testCounts);
        System.out.println("Candidates after sorting: ");
        for (int i = 0; i < testCandidates.length; i++) {
            System.out.println("  User " + testCandidates[i] 
                + " -> mutual count: " + testCounts[i]);
        }

        // Test getRecommendations
        System.out.println("\n--- Testing getRecommendations ---");
        System.out.println("Top 3 recommendations for Alice(0):");
        int[] recommendations = getRecommendations(0, 3);
        String[] names = {"Alice", "Bob", "Charlie", "Diana", "Eve", "Frank"};
        for (int i = 0; i < recommendations.length; i++) {
            System.out.println("  " + (i+1) + ". " 
                + names[recommendations[i]] 
                + " (User " + recommendations[i] + ")"
                + " - Mutual friends: " 
                + countMutualFriends(0, recommendations[i]));
        }

        System.out.println("\n=== Demo Complete ===");
    }
}