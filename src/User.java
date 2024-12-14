/**
 * Represents the user of the auction system.
 * Demonstrates Single Responsibility Principle by encapsulating user-related behavior.
 */
public class User {
    private int userId;                     // Unique identifier
    private String userName;                // User's name

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    // Getters and Setters (Encapsulation: Controlled access)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Equals and HashCode based on userId (Ensures identity for equality checks)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        User user = (User) obj;
        return userId == user.userId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(userId);
    }
}