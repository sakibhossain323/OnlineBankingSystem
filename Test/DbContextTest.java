import Model.Customer;
import Repository.DbContext;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DbContextTest {
    @Test
    public void getConnectionTest() throws ClassNotFoundException {

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

//    @Test
//    public void insertCustomerTest() throws ClassNotFoundException {
//        DbContext db = new DbContext();
//        Customer c = new Customer("khalid", "123", "khalid@gmail.com");
//        //------------------------------------------------------------------------
//
//        String sql = "INSERT INTO CUSTOMER VALUES (?,?,?,?,?)";
//
//        try (Connection conn = db.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setInt(1, c.getId());
//            ps.setString(2, c.getName());
//            ps.setString(3, "khalider basha");
//            ps.setString(4, c.getEmail());
//            ps.setString(5, c.getPhone());
//
//            int rows = ps.executeUpdate();
//            assertEquals(1, rows);
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}