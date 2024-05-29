package Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbContext {
    public DbContext() throws ClassNotFoundException
    {
        Class . forName ("oracle.jdbc.driver.OracleDriver");
    }

    public Connection getConnection() throws SQLException
    {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "project";
        String password = "gg";

        return DriverManager.getConnection(url, username, password);
    }
}
