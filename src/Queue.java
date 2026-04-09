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
        front = null;
        rear = null;
        size = 0;
        
    }

    // Add element to rear of queue
    public void enqueue(int data) {
        Node newNode = new Node(data);

if (rear == null) {
    front = newNode;
    rear = newNode;
} else {
    rear.next = newNode;
    rear = newNode;
}

size++;
        
    }

    // Remove and return element from front of queue
    public int dequeue() {
        if (isEmpty()) {
    System.out.println("Queue is empty");
    return -1;
}

int value = front.data;
front = front.next;

if (front == null) {
    rear = null;
}

size--;
return value;
             
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return front == null; 
    }

    // Return front element without removing
    public int peek() {
        if (isEmpty()) {
    System.out.println("Queue is empty");
    return -1;
}

return front.data;
        
    }
}