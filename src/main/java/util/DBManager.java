package util;

import app.Configuration;
import models.User;
import java.sql.*;

// SQLite Java ->
// https://www.tutorialspoint.com/sqlite/sqlite_java.htm

public class DBManager {

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

                if (setLogin.equals(login) && setPassword.equals(password)) {
                    User user = new User(setID, setLogin);
                    StateManager.update("user", user);

                    return;
                }

                User user = new User(-1, "");
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
}
