package org.orbitr.dal.repositories;

import org.orbitr.dal.config.DatabaseConnection;
import org.orbitr.dal.models.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static UserRepository instance;
    private UserRepository(){
    }

    public static UserRepository getInstance(){
        if (instance == null) {
            instance = new UserRepository();
        }

        return instance;
    }

    public void createUser(User user) {
        String sql = "INSERT INTO Users (FirstName, LastName, Username, Password, Email, ProfilePicture, Credits) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getProfilePicture());
            statement.setInt(7, user.getCredits());

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database access error occurred.", e);
        }
    }

    public User getUser(int id) {
        String sql = "SELECT * FROM Users WHERE ID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("ID"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Username"),
                        resultSet.getString("Password"),
                        resultSet.getString("Email"),
                        resultSet.getString("ProfilePicture"),
                        resultSet.getInt("Credits")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading user from the database.", e);
        }
        return null;
    }

    public void updateUser(User user) {
        String sql = "UPDATE Users SET FirstName = ?, LastName = ?, Username = ?, Password = ?, Email = ?, ProfilePicture = ?, Credits = ? WHERE ID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getProfilePicture());
            statement.setInt(7, user.getCredits());
            statement.setInt(8, user.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating user in the database.", e);
        }
    }

    public void deleteUser(int id) {
        String sql = "DELETE FROM Users WHERE ID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user from the database.", e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("ID"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Username"),
                        resultSet.getString("Password"),
                        resultSet.getString("Email"),
                        resultSet.getString("ProfilePicture"),
                        resultSet.getInt("Credits")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all users from the database.", e);
        }
        return users;
    }
}
