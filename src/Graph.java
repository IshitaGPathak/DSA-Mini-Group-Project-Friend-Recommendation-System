// Author: Sumangal Tengse (1012411234)
// Module: Graph Structure
// Description: Adjacency list representation of social network
//              using manually implemented linked list

public class Graph {

    class Node {
        int userId;
        Node next;

        Node(int userId) {
            this.userId = userId;
            this.next = null;
        }
    }

    Node[] adjList;
    User[] users;
    int totalUsers;

    public Graph(int totalUsers) {
        this.totalUsers = totalUsers;
        adjList = new Node[totalUsers];
        users = new User[totalUsers];
        for (int i = 0; i < totalUsers; i++) {
            adjList[i] = null;
        }
    }

    public void addUser(int userId, String name) {
        users[userId] = new User(userId, name);
    }

    public void addFriendship(int userId1, int userId2) {
        Node node1 = new Node(userId2);
        node1.next = adjList[userId1];
        adjList[userId1] = node1;

        Node node2 = new Node(userId1);
        node2.next = adjList[userId2];
        adjList[userId2] = node2;
    }

    public void removeFriendship(int userId1, int userId2) {
        adjList[userId1] = removeFromList(adjList[userId1], userId2);
        adjList[userId2] = removeFromList(adjList[userId2], userId1);
    }

    private Node removeFromList(Node head, int targetId) {
        if (head == null) return null;
        if (head.userId == targetId) return head.next;

        Node current = head;
        while (current.next != null) {
            if (current.next.userId == targetId) {
                current.next = current.next.next;
                return head;
            }
            current = current.next;
        }
        return head;
    }

    public Node getFriends(int userId) {
        return adjList[userId];
    }

    public boolean areFriends(int userId1, int userId2) {
        Node current = adjList[userId1];
        while (current != null) {
            if (current.userId == userId2) return true;
            current = current.next;
        }
        return false;
    }

    public void displayFriends(int userId) {
        System.out.print("Friends of " + users[userId].name + ": ");
        Node current = adjList[userId];
        if (current == null) {
            System.out.println("No friends yet.");
            return;
        }
        while (current != null) {
            System.out.print(users[current.userId].name + " ");
            current = current.next;
        }
        System.out.println();
    }
}