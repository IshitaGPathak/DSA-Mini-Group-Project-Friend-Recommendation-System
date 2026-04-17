// Author: Sandali Kurhade (1012411240)
// Module: BFS Implementation
// Description: Breadth First Search traversal on social network graph
//              Used to find friends of friends for recommendations

public class BFS {

    private Graph graph;

    public BFS(Graph graph) {
        this.graph = graph;
    }

    // BFS traversal from source user
    // Returns array of visited user IDs in BFS order
    public int[] bfsTraversal(int sourceId) {
        boolean[] visited = new boolean[graph.totalUsers];
        int[] result = new int[graph.totalUsers];
        int resultCount = 0;

        Queue queue = new Queue();
        visited[sourceId] = true;
        queue.enqueue(sourceId);

        while (!queue.isEmpty()) {
            int current = queue.dequeue();
            result[resultCount++] = current;

            Graph.Node friend = graph.getFriends(current);
            while (friend != null) {
                if (!visited[friend.userId]) {
                    visited[friend.userId] = true;
                    queue.enqueue(friend.userId);
                }
                friend = friend.next;
            }
        }

        // Trim result array
        int[] trimmed = new int[resultCount];
        for (int i = 0; i < resultCount; i++) {
            trimmed[i] = result[i];
        }
        return trimmed;
    }

    // Get friends of friends excluding direct friends and self
    public int[] getFriendsOfFriends(int sourceId) {
        boolean[] isFriend = new boolean[graph.totalUsers];
        boolean[] visited = new boolean[graph.totalUsers];
        int[] result = new int[graph.totalUsers];
        int count = 0;

        // Mark direct friends
        Graph.Node f = graph.getFriends(sourceId);
        while (f != null) {
            isFriend[f.userId] = true;
            f = f.next;
        }

        // Find friends of friends
        Graph.Node friend = graph.getFriends(sourceId);
        while (friend != null) {
            Graph.Node fof = graph.getFriends(friend.userId);
            while (fof != null) {
                if (fof.userId != sourceId && !isFriend[fof.userId]
                        && !visited[fof.userId]) {
                    result[count++] = fof.userId;
                    visited[fof.userId] = true;
                }
                fof = fof.next;
            }
            friend = friend.next;
        }

        int[] trimmed = new int[count];
        for (int i = 0; i < count; i++) {
            trimmed[i] = result[i];
        }
        return trimmed;
    }
}