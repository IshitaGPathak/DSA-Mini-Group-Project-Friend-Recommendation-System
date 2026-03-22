// Author: Sumangal Tengse (1012411234)
// Module: Graph Structure
// Description: Adjacency list representation of social network
//              using manually implemented linked list

public class Graph {

    // Manual linked list node for adjacency list
    class Node {
        int userId;
        Node next;

        Node(int userId) {
            this.userId = userId;
            this.next = null;
        }
    }

    // Array of linked list heads — one per user
    Node[] adjList;
    int totalUsers;

    public Graph(int totalUsers) {
        // TODO
    }

    // Add friendship between two users (undirected edge)
    public void addFriendship(int userId1, int userId2) {
        // TODO
    }

    // Remove friendship between two users
    public void removeFriendship(int userId1, int userId2) {
        // TODO
    }

    // Get head of adjacency linked list for a user
    public Node getFriends(int userId) {
        // TODO
        return null;
    }

    // Check if two users are already friends
    public boolean areFriends(int userId1, int userId2) {
        // TODO
        return false;
    }
}