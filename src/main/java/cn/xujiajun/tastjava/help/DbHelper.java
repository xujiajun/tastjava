package cn.xujiajun.tastjava.help;

import cn.xujiajun.tastjava.util.PropsUtil;

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
            Properties conf = PropsUtil.loadProps("config.properties");
            jdbcURL = conf.getProperty("jdbc.url");
            jdbcUsername = conf.getProperty("jdbc.username");
            jdbcPassword = conf.getProperty("jdbc.password");
    }

    public static Connection getConnect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

        }

        return jdbcConnection;
    }

    public static void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
}
