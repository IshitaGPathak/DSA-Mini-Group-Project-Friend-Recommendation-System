# Data Structure Design

## Graph Representation
- Adjacency list using manually implemented linked list
- Each user is identified by an integer ID
- Friendships are undirected edges (if A is friend of B, B is friend of A)

## Queue
- Manually implemented using linked list
- Used by BFS for level-by-level traversal
- Operations: enqueue, dequeue, isEmpty, peek

## BFS
- Standard BFS from source user
- Visited array to avoid revisiting nodes
- Returns all users within 2 degrees of connection

## Recommendation Engine
- Iterates over BFS output (friends of friends)
- For each candidate counts mutual friends with source user
- Uses insertion sort to rank candidates by mutual count
- Returns top N recommendations

## Time Complexity Summary
| Operation | Complexity |
|-----------|-----------|
| BFS Traversal | O(V + E) |
| Mutual Friend Count | O(D) |
| Sorting Candidates | O(R²) |
| Get Recommendations | O(V + E + R²) |