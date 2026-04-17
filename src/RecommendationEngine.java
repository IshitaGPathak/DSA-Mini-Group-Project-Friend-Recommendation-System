// Author: Ishita Pathak (1012411228)
// Module: Recommendation Engine
// Description: Generates friend recommendations based on mutual friend count
//              Candidates are obtained from BFS, ranked by mutual friend count
// Reference: Cormen et al., Introduction to Algorithms, 3rd Ed.
//            Graph traversal and sorting — Chapter 22, Chapter 2

public class RecommendationEngine {

    private Graph graph;
    private BFS bfs;

    public RecommendationEngine(Graph graph, BFS bfs) {
        this.graph = graph;
        this.bfs = bfs;
    }

    // Count mutual friends between source user and a candidate user
    // Traverse source's friend list and check if each friend
    // exists in candidate's friend list
    // Time Complexity: O(D^2) where D = average degree of a node
    public int countMutualFriends(int sourceId, int candidateId) {
        int mutualCount = 0;

        // Get friend list of source user
        Graph.Node sourceFriend = graph.getFriends(sourceId);

        // Traverse each friend of source
        while (sourceFriend != null) {

            // Check if this friend exists in candidate's friend list
            Graph.Node candidateFriend = graph.getFriends(candidateId);

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

    // Sort candidates by mutual friend count using insertion sort
// Insertion sort chosen for simplicity on small datasets
// Sorts in descending order — highest mutual count first
// Time Complexity: O(R^2) where R = number of candidates
private void sortByMutualCount(int[] candidates, int[] mutualCounts) {
    int n = candidates.length;

    for (int i = 1; i < n; i++) {
        int keyCount = mutualCounts[i];
        int keyCandidate = candidates[i];
        int j = i - 1;

        // Shift elements with smaller mutual count to the right
        while (j >= 0 && mutualCounts[j] < keyCount) {
            mutualCounts[j + 1] = mutualCounts[j];
            candidates[j + 1] = candidates[j];
            j--;
        }

        mutualCounts[j + 1] = keyCount;
        candidates[j + 1] = keyCandidate;
    }
}

    // Return top N friend recommendations for a given user
    // Return top N friend recommendations for a given user
// Uses friends of friends as candidate pool
// Ranks by mutual friend count in descending order
// Time Complexity: O(V + E + R^2) where R = candidates
public int[] getRecommendations(int sourceId, int topN) {

    boolean[] isFriend = new boolean[graph.totalUsers];
    boolean[] visited = new boolean[graph.totalUsers];

    // Mark direct friends of source
    Graph.Node f = graph.getFriends(sourceId);
    while (f != null) {
        isFriend[f.userId] = true;
        f = f.next;
    }

    // Find friends of friends as candidates
    int[] candidates = new int[graph.totalUsers];
    int[] mutualCounts = new int[graph.totalUsers];
    int count = 0;

    Graph.Node friend = graph.getFriends(sourceId);
    while (friend != null) {
        Graph.Node fof = graph.getFriends(friend.userId);
        while (fof != null) {
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

    // Trim arrays to actual count
    int[] trimmedCandidates = new int[count];
    int[] trimmedCounts = new int[count];
    for (int i = 0; i < count; i++) {
        trimmedCandidates[i] = candidates[i];
        trimmedCounts[i] = mutualCounts[i];
    }

    // Sort by mutual count
    sortByMutualCount(trimmedCandidates, trimmedCounts);

    // Return top N
    int resultSize = Math.min(topN, count);
    int[] result = new int[resultSize];
    for (int i = 0; i < resultSize; i++) {
        result[i] = trimmedCandidates[i];
    }
    return result;
}
}