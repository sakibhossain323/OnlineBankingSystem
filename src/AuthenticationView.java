import java.util.Scanner;

public class AuthenticationView implements IAuthenticationView {

    ICustomerService customerService;
    IAuthenticationService authenticationService;

    public AuthenticationView(ICustomerService customerService, IAuthenticationService authenticationService) {
        this.customerService = customerService;
        this.authenticationService = authenticationService;
    }

    @Override
    public void register() {
        String name = Reader.readField("name");
        String phone = Reader.readField("phone");
        String email = Reader.readField("email");
        Customer customer = customerService.createCustomer(name, phone, email);
        String password = Reader.readField("password");
        authenticationService.createRecord(customer, password);
        System.out.println("Registered Successfully...");
    }

    @Override
    public Customer login() {
        String phone = Reader.readField("phone");
        Customer customer = customerService.getCustomerByPhone(phone);
        String password = Reader.readField("password");
        if(authenticationService.isValid(customer, password)) {
            return customer;
        }
        throw new RuntimeException("Invalid credentials");
    }
}
