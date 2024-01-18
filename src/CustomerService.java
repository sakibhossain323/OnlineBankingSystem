public class CustomerService implements ICustomerService {
    ICustomerRepository customerRepository;

    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(String name, String phone, String email) {
        Customer customer = new Customer(name, phone, email);
        customerRepository.createCustomer(customer);
        return customer;
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerRepository.getCustomer(id);
    }

    @Override
    public Customer getCustomerByPhone(String phone) {
        return customerRepository.getCustomer(phone);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerRepository.getCustomer(email);
    }
}
