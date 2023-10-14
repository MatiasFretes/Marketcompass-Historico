package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseConnector {
    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public DatabaseConnector() {
        loadProperties();
    }

    private void loadProperties() {
        try (FileInputStream input = new FileInputStream("src/main/resources/database.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            dbUrl = properties.getProperty("db.url");
            dbUser = properties.getProperty("db.user");
            dbPassword = properties.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
