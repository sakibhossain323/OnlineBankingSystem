import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class CustomerRepository implements ICustomerRepository{
    List<Customer> customers;

    public CustomerRepository() {
        customers = new ArrayList<>();
    }

    public CustomerRepository(List<Customer> customers) {
        this.customers = customers;
    }

    public Customer getCustomer(int id)
    {
        Optional<Customer> result = customers.stream().
                filter((c)-> c.getId() == id).findFirst();
        return result.orElse(null);
    }

    public void addCustomer(Customer customer){
        OptionalInt id = customers.stream().mapToInt(Customer::getId).max();
        customer.setId(id.orElse(0)+1);
        customers.add(customer);
    }
}
