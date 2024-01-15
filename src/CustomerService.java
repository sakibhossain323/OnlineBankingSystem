public class CustomerService implements ICustomerService {
    ICustomerRepository customerRepository;

    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void createCustomer(String name, String phone, String email) {
        Customer customer = new Customer(name, phone, email);
        customerRepository.createCustomer(customer);
    }

    @Override
    public Customer getCustomer(int id) {
        return customerRepository.getCustomer(id);
    }
}
