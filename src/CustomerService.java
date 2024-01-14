public class CustomerService implements ICustomerService {
    ICustomerRepository customerRepository = null;

    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void addCustomer(String name, String phone, String email) {
        Customer customer = new Customer(name, phone, email);
        customerRepository.addCustomer(customer);
    }

    @Override
    public Customer getCustomer(int id) {
        return customerRepository.getCustomer(id);
    }
}
