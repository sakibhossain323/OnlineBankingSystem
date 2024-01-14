import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class customerRepository implements ICustomerRepository{
    List<Customer> customers;

    public Customer getCustomer(int id)
    {
        Optional<Customer> result = customers.stream().
                filter((c)-> c.id == id).findFirst();
        return result.orElse(null);
    }

    public void addCustomer(Customer customer){
        OptionalInt id = customers.stream().mapToInt((c)-> c.id).max();
        customer.setId(id.orElse(1));
        customers.add(customer);
    }
}
