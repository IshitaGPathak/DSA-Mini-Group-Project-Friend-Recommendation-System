# Friend Recommendation System
### Using Breadth First Search (BFS) and Mutual Friend Count

> **Course:** Data Structures 2  
> **Branch:** B.Tech Computer Science Engineering — Division C  
> **College:** DES Pune University  

---

## Group Members

| Roll Number | Name | Module |
|-------------|------|--------|
| 1012411228 | Ishita Pathak | Recommendation Engine |
| 1012411234 | Sumangal Tengse | Graph Structure |
| 1012411240 | Sandali Kurhade | BFS Implementation |
| 1012512015 | Suruchi Dhawan | UI + Main Program |
| 1012411203 | Rishab Mehta | Testing + Documentation |

---

## Problem Statement

Social networks like Instagram, LinkedIn and Facebook suggest people you may know. The core idea behind this feature is simple — if two people have many mutual friends, they are likely to know each other too.

This project implements a **Friend Recommendation System** that models a social network as a graph, uses **Breadth First Search (BFS)** to find friends of friends, and ranks recommendations based on **mutual friend count**.

All data structures are implemented manually without relying on built-in Java libraries.

---

## Data Structures Used

| Data Structure | Purpose |
|---------------|---------|
| **Graph (Adjacency List)** | Represents the social network — each user is a node, each friendship is an edge |
| **Queue (Manual)** | Used internally by BFS for level-by-level traversal |
| **HashMap** | Maps user IDs to their friend lists |
| **ArrayList** | Stores friend lists and recommendation results |
| **Visited Array** | Tracks visited nodes during BFS to avoid infinite loops |

---

## Core Operations to Implement

- Add a user to the network
- Add/remove a friendship between two users
- Display a user's friend list
- Run BFS from a given user to find friends of friends
- Count mutual friends between two users
- Generate top N friend recommendations ranked by mutual friend count
- Display the full social network graph

---

## Algorithm Plan

### Step 1 — Build the Graph
Model the social network as an **undirected graph** using an adjacency list.
Each user = node. Each friendship = edge (bidirectional).

### Step 2 — BFS Traversal
Starting from a given user:
1. Add the user to a queue
2. Mark them as visited
3. Dequeue the front user
4. Add all their unvisited friends to the queue
5. Repeat until queue is empty

This finds all users reachable within N degrees of connection.

### Step 3 — Mutual Friend Count
For each friend-of-friend (potential recommendation):
- Find their friend list
- Count how many friends they share with the source user
- Store as `(candidate, mutualCount)` pair

### Step 4 — Rank Recommendations
Sort candidates by mutual friend count in descending order.
Return top N recommendations.

### Time Complexity
| Operation | Complexity |
|-----------|-----------|
| BFS Traversal | O(V + E) |
| Mutual Friend Count | O(D²) where D = average degree |
| Sorting Recommendations | O(R log R) where R = number of candidates |

---

## Module Division and Responsibilities

### Module 1 — Graph Structure
**Member:** Sumangal Tengse (1012411234)
- Implement `User` class with ID and name
- Implement `Graph` class with adjacency list
- Methods: `addUser()`, `addFriendship()`, `removeFriendship()`, `getFriends()`

### Module 2 — BFS Implementation
**Member:** Sandali Kurhade (1012411240)
- Implement manual `Queue` class from scratch
- Implement `BFS` class with traversal logic
- Methods: `bfsTraversal()`, `getFriendsOfFriends()`, `getVisited()`

### Module 3 — Recommendation Engine
**Member:** Ishita Pathak (1012411228)
- Implement `RecommendationEngine` class
- Count mutual friends between source user and candidates
- Rank and return top N recommendations
- Methods: `countMutualFriends()`, `getRecommendations()`, `rankCandidates()`

### Module 4 — UI + Main Program
**Member:** Suruchi Dhawan (1012512015)
- Implement menu-driven console interface
- Handle all user input and output
- Integrate all modules into working program
- Methods: `displayMenu()`, `handleInput()`, `displayRecommendations()`

### Module 5 — Testing + Documentation
**Member:** Rishab Mehta (1012411203)
- Create sample social network test data (minimum 10 users)
- Write test cases for all core operations
- Add comments throughout codebase
- Verify edge cases (user with no friends, disconnected graph)

---

## Project Structure

```
FriendRecommendationSystem/
├── src/
│   ├── User.java                  # User node class
│   ├── Graph.java                 # Graph with adjacency list
│   ├── Queue.java                 # Manual queue implementation
│   ├── BFS.java                   # BFS traversal logic
│   ├── RecommendationEngine.java  # Mutual friend count + ranking
│   ├── Main.java                  # Menu driven UI + integration
│   └── TestCases.java             # Test data and test cases
├── README.md
└── docs/
    └── design.md                  # Data structure design notes
```

---

## Development Timeline

| Week | Tasks | Members |
|------|-------|---------|
| **Week 1** | Repository setup, README, algorithm design, module planning | All members |
| **Week 2** | Code individual modules, integrate, test, push with regular commits | All members |
| **Week 3** | Final testing, bug fixes, demo preparation, viva prep | All members |

---

## Sample Test Case

```
Users: Alice, Bob, Charlie, Diana, Eve, Frank

Friendships:
Alice — Bob
Alice — Charlie
Bob — Diana
Bob — Eve
Charlie — Frank
Diana — Eve

Query: Recommend friends for Alice

BFS finds: Diana, Eve, Frank (friends of friends)

Mutual friend count:
Diana → 1 mutual (Bob)
Eve   → 1 mutual (Bob)
Frank → 1 mutual (Charlie)

Recommendation: Diana, Eve, Frank (all tied at 1 mutual)
```

---

## How to Run

```bash
# Compile
javac src/*.java

# Run
java -cp src Main
```

---

 
*Data Structures 2 Mini Project — DES Pune University*
