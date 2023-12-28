package util;

import app.Configuration;
import models.Place;
import models.User;
import java.sql.*;
import java.util.ArrayList;

// SQLite Java ->
// https://www.tutorialspoint.com/sqlite/sqlite_java.htm

public class DBManager {
    public static void updateUserInfo(int userId, String login, String fio) {
        Connection _conn;
        Statement _statement;

        try {
            _conn = DriverManager.getConnection(Configuration.Database.driver + DBManager.class.getResource(Configuration.Database.location).toExternalForm());
            _statement = _conn.createStatement();

            String SQL = String.format("""
                    UPDATE Users
                        set LOGIN = '%s'
                        
                    WHERE ID = %s
                    """, login, userId);

            _statement.executeUpdate(SQL);

            _statement.close();
            _conn.close();

        } catch (SQLException error) {
            System.out.println(error);
        }
    }

    public static void authUser(String login, String password) {
        Connection _conn;
        Statement _statement;

        try {
            _conn = DriverManager.getConnection(Configuration.Database.driver + DBManager.class.getResource(Configuration.Database.location).toExternalForm());
            _statement = _conn.createStatement();

            String SQL = """
                    SELECT * FROM Users;
                    """;

            ResultSet set = _statement.executeQuery(SQL);

            while (set.next()) {
                int setID = set.getInt("ID");
                String setLogin = set.getString("LOGIN");
                String setPassword = set.getString("PASSWORD");
                String setFIO = set.getString("FIO");

                if (setLogin.equals(login) && setPassword.equals(password)) {
                    User user = new User(setID, setLogin, setFIO);
                    StateManager.update("user", user);

                    return;
                }

                User user = new User(-1, "", "");
                StateManager.update("user", user);
            }

            set.close();
            _statement.close();
            _conn.close();

        } catch (SQLException ignored) {}
    }

    public static void createUser(String login, String password) {
        Connection _conn;
        Statement _statement;

        try {
            _conn = DriverManager.getConnection(Configuration.Database.driver + DBManager.class.getResource(Configuration.Database.location).toExternalForm());
            _statement = _conn.createStatement();

            String SQL = String.format("""
                    INSERT INTO Users (LOGIN, PASSWORD)
                    VALUES ('%s', '%s');
                    """, login, password);

            _statement.executeUpdate(SQL);
            _statement.close();
            _conn.close();

            StateManager.update("user_registered", true);

        } catch (SQLException ignored) {
            StateManager.update("user_registered", false);
        }
    }

    public static void initTables() {
        Connection _conn;
        Statement _statement;

        try {
            _conn = DriverManager.getConnection(Configuration.Database.driver + DBManager.class.getResource(Configuration.Database.location).toExternalForm());
            _statement = _conn.createStatement();

            for (String tableSQL : Configuration.Database.InitialTables.tableSQLs) {
                _statement.executeUpdate(tableSQL);
            }

            _statement.close();
            _conn.close();

        } catch (SQLException ignored) {}
    }

    public static void getAllPlaces() {
        Connection _conn;
        Statement _statement;

        try {
            _conn = DriverManager.getConnection(Configuration.Database.driver + DBManager.class.getResource(Configuration.Database.location).toExternalForm());
            _statement = _conn.createStatement();

            String SQL = """
                    SELECT * FROM Places;
                    """;

            ResultSet set = _statement.executeQuery(SQL);
            ArrayList<Place> places = new ArrayList<>();

            while (set.next()) {
                int setID = set.getInt("ID");
                String setTitle = set.getString("TITLE");
                String setDescription = set.getString("DESCRIPTION");
                String setCity = set.getString("CITY");
                String setImage = set.getString("IMAGE");

                Place place = new Place(setID, setTitle, setDescription, setCity, setImage);
                places.add(place);
            }

            StateManager.update("places", places);

            set.close();
            _statement.close();
            _conn.close();

        } catch (SQLException ignored) {}
    }

//    INSERT INTO Places(Title, Description, City, Image) VALUES
//    ("Колодец", "Самый классный колодец", "Альметьевск", "https://gymnasia2.ru/wp-content/uploads/8/c/0/8c093929b0b0755126bcfa0c2a999b8c.jpeg");
}
