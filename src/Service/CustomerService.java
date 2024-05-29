package Service;

import Model.Customer;
import Repository.ICustomerRepository;
import Service.ICustomerService;

public class CustomerService implements ICustomerService {
    ICustomerRepository customerRepository;

    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(String name, String phone, String email, String address)
    {
        Customer existing = customerRepository.getCustomerByPhone(phone);
        if(existing != null) throw new RuntimeException("phone number is already registered");
        Customer customer = new Customer(name, address, email, phone );

        customerRepository.createCustomer(customer);
        return customer;
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerRepository.getCustomerById(id);
    }

    @Override
    public Customer getCustomerByPhone(String phone) {
        return customerRepository.getCustomerByPhone(phone);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerRepository.getCustomerByEmail(email);
    }

    public int getNewId()
    {
        return customerRepository.getNewId();
    }
}
