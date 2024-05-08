import Model.Customer;
import Repository.CustomerRepository;
import Repository.DbContext;
import Service.CustomerService;
import Service.ICustomerService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {
    @Test
    public void createCustomerTest() throws ClassNotFoundException {
        ICustomerService service = new CustomerService(new CustomerRepository(new DbContext()));
        service.createCustomer("Alice", "096", "a@b.c");
        service.createCustomer("Bob", "097", "b@c.d");
        Customer customer = service.getCustomerById(2);
        assertEquals("Bob", customer.getName());
    }
}