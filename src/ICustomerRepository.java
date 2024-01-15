public interface ICustomerRepository {
    void createCustomer(Customer customer);

    Customer getCustomer(int id);
}

