import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class CustomerRepository implements ICustomerRepository{
    List<Customer> customers;

    public CustomerRepository() {
        customers = new ArrayList<>();
    }

    @Override
    public void createCustomer(Customer customer){
        OptionalInt id = customers.stream().mapToInt(Customer::getId).max();
        customer.setId(id.orElse(0)+1);
        this.customers.add(customer);
    }

    @Override
    public Customer getCustomerById(int id)
    {
        Optional<Customer> result = customers.stream().
                filter((c)-> c.getId() == id).findFirst();
        return result.orElse(null);
    }

    @Override
    public Customer getCustomerByPhone(String phone) {
        Optional<Customer> result = customers.stream().
                filter((c)-> c.getPhone().equals(phone)).findFirst();
        return result.orElse(null);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        Optional<Customer> result = customers.stream().
                filter((c)-> c.getEmail().equals(email)).findFirst();
        return result.orElse(null);
    }
}
