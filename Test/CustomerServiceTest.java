import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {
    @Test
    public void addCustomerTest()
    {
        ICustomerService service = new CustomerService(new CustomerRepository());
        service.addCustomer("Alice", "096", "a@b.c");
        service.addCustomer("Bob", "097", "b@c.d");
        Customer customer = service.getCustomer(2);
        assertEquals("Bob", customer.getName());
    }
}