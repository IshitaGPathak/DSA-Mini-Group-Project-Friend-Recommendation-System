// Author: Sumangal Tengse (1012411234)
// Module: Graph Structure
// Description: Represents a user node in the social network

public class User {

    int userId;
    String name;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " (" + userId + ")";
    }
}