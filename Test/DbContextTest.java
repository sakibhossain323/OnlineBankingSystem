import Model.Account;
import Model.Customer;
import Repository.AccountRepository;
import Repository.DbContext;
import Repository.TransactionRepository;
import Service.AccountService;
import Service.IAccountService;
import Service.ITransactionService;
import Service.TransactionService;
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

    @Test
    void transferThrowsExceptionWhenAmountGreaterThanBalanceTest() throws ClassNotFoundException {
        Customer c1 = new Customer("1", "Alice", "0967", "a@b.c");
        Customer c2 = new Customer("2", "Bob", "0968", "b@b.c");
        IAccountService service = new AccountService(new AccountRepository(new DbContext()));
        service.createAccount("SavingAccount", 500, 1, c1);
        service.createAccount("SavingAccount", 1000, 1, c2);
        Account ac1 = service.getAccount(c1, 10001);
        Account ac2 = service.getAccount(c2, 10002);
        ITransactionService trx = new TransactionService(new TransactionRepository(new DbContext()));
        Assertions.assertThrows(RuntimeException.class, () -> {
            trx.transfer(ac1, ac2, 600);
        });
    }

    @Test
    void transferDoesNotChangeSenderBalanceWhenAmountGreaterThanBalanceTest() throws ClassNotFoundException {
        Customer c1 = new Customer("1", "Alice", "0967", "a@b.c");
        Customer c2 = new Customer("2", "Bob", "0968", "b@b.c");
        IAccountService service = new AccountService(new AccountRepository(new DbContext()));
        service.createAccount("SavingAccount", 500, 1, c1);
        service.createAccount("SavingAccount", 1000, 1, c2);
        Account ac1 = service.getAccount(c1, 10001);
        Account ac2 = service.getAccount(c2, 10002);
        ITransactionService trx = new TransactionService(new TransactionRepository(new DbContext()));
        double initialBalance = ac1.getBalance();
        try {
            trx.transfer(ac1, ac2, 600);
        } catch (RuntimeException ignored) {
        }
        assertEquals(initialBalance, ac1.getBalance());
    }

    @Test
    void transferDoesNotChangeReceiverBalanceWhenAmountGreaterThanBalanceTest() throws ClassNotFoundException {
        Customer c1 = new Customer("1", "Alice", "0967", "a@b.c");
        Customer c2 = new Customer("2", "Bob", "0968", "b@b.c");
        IAccountService service = new AccountService(new AccountRepository(new DbContext()));
        service.createAccount("SavingAccount", 500, 1, c1);
        service.createAccount("SavingAccount", 1000, 1, c2);
        Account ac1 = service.getAccount(c1, 10001);
        Account ac2 = service.getAccount(c2, 10002);
        ITransactionService trx = new TransactionService(new TransactionRepository(new DbContext()));
        double initialBalance = ac2.getBalance();
        try {
            trx.transfer(ac1, ac2, 600);
        } catch (RuntimeException ignored) {
        }
        assertEquals(initialBalance, ac2.getBalance());
    }
















}