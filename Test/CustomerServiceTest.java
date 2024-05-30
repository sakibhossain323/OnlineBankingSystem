import Model.Customer;
import Repository.CustomerRepository;
import Repository.DbContext;
import Service.CustomerService;
import Service.ICustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CustomerServiceTest {
    @Test
    public void createCustomerTest() throws ClassNotFoundException {
        ICustomerService service = new CustomerService(new CustomerRepository(new DbContext()));
        service.createCustomer("Alice","095","a@b.c","abc");
        service.createCustomer("Bob", "097", "b@c.d", "def");
        Customer customer = service.getCustomerById(service.getNewId());
        assertEquals("Bob", customer.getName());
    }

    @Test
    public void getCustomerByIdTest() throws ClassNotFoundException {
        ICustomerService service = new CustomerService(new CustomerRepository(new DbContext()));
        service.createCustomer("Alice", "095", "a@b.c", "abc");
        service.createCustomer("Bob", "097", "b@c.d", "def");
        Customer customer = service.getCustomerById(service.getNewId());
        assertEquals(1018, customer.getId());






    }

    @Test
    public void getCustomerByPhoneTest() throws ClassNotFoundException {
        ICustomerService service = new CustomerService(new CustomerRepository(new DbContext()));
       Customer customer = service.createCustomer("Alice", "095", "a@b.c", "abc");
        service.createCustomer("Bob", "097", "b@c.d", "def");
        //Customer customer = service.getCustomerByPhone(service.getCustomerById(1018).getPhone());
        assertEquals("Alice", customer.getName());
    }


    @Test

    public void getCustomerByEmailTest() throws ClassNotFoundException {
        ICustomerService service = new CustomerService(new CustomerRepository(new DbContext()));
        service.createCustomer("Alice", "095", "a@b.c", "abc");
        service.createCustomer("Bob", "097", "b@c.d", "def");
        Customer customer = service.getCustomerByEmail(service.getCustomerById(1018).getEmail());
        assertEquals("Alice", customer.getName());
    }


    @Test
    public void getNewIdTest() throws ClassNotFoundException {
        ICustomerService service = new CustomerService(new CustomerRepository(new DbContext()));
        service.createCustomer("Alice", "095", "a@b.c", "abc");
        Customer customer= service.createCustomer("Bob", "097", "b@c.d", "def");
        assertEquals( service.getNewId(),customer.getId());
    }


    @Test
    void createCustomerThrowsExceptionWhenPhoneExistsTest() throws ClassNotFoundException {
        ICustomerService service = new CustomerService(new CustomerRepository(new DbContext()));
        service.createCustomer("Alice", "095", "a@b.c", "abc");
        Assertions.assertThrows(RuntimeException.class, () -> {
            service.createCustomer("Bob", "095", "b@c.d", "def");
        });
    }

    @Test
    void getCustomerByPhoneReturnsCorrectCustomerTest() throws ClassNotFoundException {
        ICustomerService service = new CustomerService(new CustomerRepository(new DbContext()));
        service.createCustomer("Alice", "095", "a@b.c", "abc");
        Customer customer = service.getCustomerByPhone("095");
        assertEquals("Alice", customer.getName());
    }

    @Test
    void getCustomerByEmailReturnsCorrectCustomerTest() throws ClassNotFoundException {
        ICustomerService service = new CustomerService(new CustomerRepository(new DbContext()));
        service.createCustomer("Alice", "095", "a@b.c", "abc");
        Customer customer = service.getCustomerByEmail("a@b.c");
        assertEquals("Alice", customer.getName());
    }








}