import java.util.Scanner;

public class AuthenticationView implements IAuthenticationView {

    ICustomerService customerService;
    IAuthenticationService authenticationService;

    public AuthenticationView(ICustomerService customerService, IAuthenticationService authenticationService) {
        this.customerService = customerService;
        this.authenticationService = authenticationService;
    }

    private String readField(String fieldName)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter "+fieldName+": ");
        return scanner.nextLine();
    }

    @Override
    public void register() {
        String name = readField("name");
        String phone = readField("phone");
        String email = readField("email");
        Customer customer = customerService.createCustomer(name, phone, email);
        authenticationService.createRecord(customer, readField("Password"));
        System.out.println("Registered Successfully...");
    }

    @Override
    public Customer login() {
        String phone = readField("phone");
        Customer customer = customerService.getCustomerByPhone(phone);
        String password = readField("password");
        if(authenticationService.isValid(customer, password)) {
            return customer;
        }
        throw new RuntimeException("Invalid credentials");
    }
}
