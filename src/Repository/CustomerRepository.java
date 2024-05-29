package Repository;

import Model.Customer;
import Repository.ICustomerRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class CustomerRepository implements ICustomerRepository {
    DbContext db;
    List<Customer> customers;

    public CustomerRepository(DbContext db) {
        this.db = db;
        customers = new ArrayList<>();
    }

    @Override
    public void createCustomer(Customer customer)
    {
        String sql = "INSERT INTO customer VALUES (?,?,?,?,?)";

        try (var conn = db.getConnection();
             var ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, 0);
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getEmail());
            ps.setString(5, customer.getPhone());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        OptionalInt id = customers.stream().mapToInt(Customer::getId).max();
//        customer.setId(id.orElse(0)+1);
//        this.customers.add(customer);
    }

    @Override
    public Customer getCustomerById(int id)
    {
        String sql = "SELECT * FROM customer WHERE customer_id = " + id;

        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return new Customer(rs.getInt("customer_id"), rs.getString("customer_name"),
                        rs.getString("customer_address"), rs.getString("customer_email"), rs.getString("customer_phone"));
            } throw new RuntimeException("No customer found");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer getCustomerByPhone(String phone) {
        Optional<Customer> result = customers.stream().
                filter((c)-> c.getPhone().equals(phone)).findFirst();
        return result.orElse(null);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        Optional<Customer> result = customers.stream().
                filter((c)-> c.getEmail().equals(email)).findFirst();
        return result.orElse(null);
    }

    public int getNewId()
    {
        String sql = "SELECT MAX(customer_id) FROM customer";

        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt(1);
            } throw new RuntimeException("No customer found");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
