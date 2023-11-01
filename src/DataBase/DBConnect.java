package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    public static Connection connectToDB() {
        Connection connection = null;
        String url = "jdbc:sqlserver://UMINDU-LAPTOP\\SQLEXPRESS;databaseName=RestoPOS;integratedSecurity=true;encrypt=false;";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection to SQL Server failed");
            e.printStackTrace();
        }
        return connection;
    }
}
