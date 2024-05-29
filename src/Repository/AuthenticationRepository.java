package Repository;

import Repository.IAuthenticationRepository;

import java.sql.*;
import java.util.HashMap;

public class AuthenticationRepository implements IAuthenticationRepository {
    DbContext db;
    HashMap<String, String> records;

    public AuthenticationRepository(DbContext db) {
        this.db = db;
        records = new HashMap<>();
    }

    @Override
    public void createRecord(int identifier, String passwordHash)
    {
        String sql = "INSERT INTO credential VALUES (?,?)";

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))
        {

            ps.setInt(1, identifier);
            ps.setString(2, passwordHash);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //records.put(identifier, passwordHash);
    }

    @Override
    public boolean isValid(int identifier, String passwordHash)
    {
        String sql = "SELECT * FROM credential WHERE customer_id = ? AND customer_password = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, identifier);
            ps.setString(2, passwordHash);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //return passwordHash.equals(records.get(identifier));
    }
}
