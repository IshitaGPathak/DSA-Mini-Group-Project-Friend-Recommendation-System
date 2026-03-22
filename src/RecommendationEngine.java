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
        // TODO
    }

    // Count mutual friends between source user and a candidate user
    // Time Complexity: O(D) where D = average degree of a node
    public int countMutualFriends(int sourceId, int candidateId) {
        // TODO
        return 0;
    }

    // Sort candidates by mutual friend count using insertion sort
    // Insertion sort chosen for simplicity and stability
    // Time Complexity: O(R^2) where R = number of candidates
    private void sortByMutualCount(int[] candidates, int[] mutualCounts) {
        // TODO
    }

    // Return top N friend recommendations for a given user
    // Uses BFS output as candidate pool
    // Ranks by mutual friend count in descending order
    public int[] getRecommendations(int sourceId, int topN) {
        // TODO
        return null;
    }
}