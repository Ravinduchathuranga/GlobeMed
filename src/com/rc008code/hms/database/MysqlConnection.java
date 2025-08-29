package com.rc008code.hms.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Singleton class for MySQL database connection
public class MysqlConnection {
    private static MysqlConnection mysqlConnection;
    private static Connection connection;

    private MysqlConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/global_med", "root", "AirbusA330-300");
    }

    public static MysqlConnection getInstance() throws SQLException, ClassNotFoundException {
        if (mysqlConnection == null) {
            mysqlConnection = new MysqlConnection();
        }
        return mysqlConnection;
    }

    public Connection getConnection() {
        return connection;
    }


}
