import Model.Customer;
import Repository.DbContext;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.sql.Connection;

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



    @Test
    public void insertCustomerTest() throws ClassNotFoundException {
        DbContext db = new DbContext();
        Customer c = new Customer("khalid", "123", "khalid@gmail.com", "123");

        String sql = "INSERT INTO CUSTOMER (name, phone, email, address) VALUES (?, ?, ?, ?)";

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getName());
            ps.setString(2, c.getPhone());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getAddress());

            int rows = ps.executeUpdate();
            assertEquals(1, rows);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    public void updateCustomerTest() throws ClassNotFoundException {
        DbContext db = new DbContext();
        Customer c = new Customer("khalid", "123", "khalid@gmail.com", "123 Street");

        String sql = "UPDATE CUSTOMER SET phone = ?, email = ?, address = ? WHERE name = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getPhone());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getAddress());
            ps.setString(4, c.getName());

            int rows = ps.executeUpdate();
            assertEquals(1, rows);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }











}