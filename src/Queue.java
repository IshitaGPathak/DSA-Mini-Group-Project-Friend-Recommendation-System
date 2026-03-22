// Author: Sandali Kurhade (1012411240)
// Module: BFS Implementation
// Description: Manually implemented Queue using linked list
//              Used by BFS for level-by-level graph traversal

public class Queue {

    // Node for the queue linked list
    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    Node front;  // Points to front of queue
    Node rear;   // Points to rear of queue
    int size;

    public Queue() {
        // TODO
    }

    // Add element to rear of queue
    public void enqueue(int data) {
        // TODO
    }

    // Remove and return element from front of queue
    public int dequeue() {
        // TODO
        return -1;
    }

    // Check if queue is empty
    public boolean isEmpty() {
        // TODO
        return true;
    }

    // Return front element without removing
    public int peek() {
        // TODO
        return -1;
    }
}