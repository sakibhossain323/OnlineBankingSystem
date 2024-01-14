import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavingAccountTest {
    @Test
    public void debitSufficientBalance() {
        ICustomer c = new Customer(1, "A", "09", "a@b.c");
        IAccount a = new SavingAccount(1, c, 100, false);
        assertDoesNotThrow(()->a.debit(50));
        assertEquals(50, a.getBalance());
    }

    @Test
    public void debitInsufficientBalance() {
        ICustomer c = new Customer(1, "A", "09", "a@b.c");
        IAccount a = new SavingAccount(1, c, 100, false);
        assertThrows(Exception.class, ()->a.debit(200));
    }
}