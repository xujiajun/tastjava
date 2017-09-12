package cn.xujiajun.tastjava.help;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbHelper {
    private static String jdbcURL;
    private static String jdbcDriver;
    private static String jdbcUsername;
    private static String jdbcPassword;
    private static Connection jdbcConnection;

    static {
        try {
            String basedir = System.getProperty("user.dir");
            InputStream input = new FileInputStream(basedir + "/src/main/resources/config.properties");

            Properties prop = new Properties();
            // load a properties file
            prop.load(input);

            // get the property value and print it out
            jdbcURL = prop.getProperty("jdbc.url");
            jdbcUsername = prop.getProperty("jdbc.username");
            jdbcPassword = prop.getProperty("jdbc.password");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static Connection getConnect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);

        }

        return jdbcConnection;
    }

    public static void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
}
