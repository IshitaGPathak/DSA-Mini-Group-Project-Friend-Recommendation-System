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
    public int[] getRecommendations(int sourceId, int topN) {
        // TODO
        return null;
    }
}