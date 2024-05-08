import Repository.DbContext;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DbContextTest {
    @Test
    public void getConnectionTest() throws ClassNotFoundException{

        DbContext db = new DbContext();

        //
        String sql = "SELECT * FROM CUSTOMER";

        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String name = rs.getString("name");
                assertEquals("khalid", name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
